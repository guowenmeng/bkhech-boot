package com.bkhech.boot.configure.reactive.webflux;

import com.bkhech.boot.configure.common.dto.ResultResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * ErrorWebExceptionHandler
 *
 * Created by guowm on 18-5-4.
 */
@Component
public class BkhechExceptionHandler extends DefaultErrorWebExceptionHandler {

    private static final Log logger = LogFactory.getLog(BkhechExceptionHandler.class);

    private static final Map<HttpStatus.Series, String> SERIES_VIEWS;

    private ErrorProperties errorProperties;

    static {
        Map<HttpStatus.Series, String> views = new EnumMap<>(HttpStatus.Series.class);
        views.put(HttpStatus.Series.CLIENT_ERROR, "4xx");
        views.put(HttpStatus.Series.SERVER_ERROR, "5xx");
        SERIES_VIEWS = Collections.unmodifiableMap(views);
    }

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public BkhechExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
        this.errorProperties = errorProperties;
    }


    @Override
    protected Mono<ServerResponse> renderErrorView(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.TEXT_HTML);
        Map<String, Object> error = getErrorAttributes(request, includeStackTrace);
        HttpStatus errorStatus = getHttpStatus(error);
        ServerResponse.BodyBuilder responseBody = ServerResponse.status(errorStatus)
                .contentType(MediaType.TEXT_HTML);
        Flux<ServerResponse> result = Flux
                .just("error/" + errorStatus.toString(),
                        "error/" + SERIES_VIEWS.get(errorStatus.series()), "error/error")
                .flatMap((viewName) -> renderErrorView(viewName, responseBody, error));
        if (this.errorProperties.getWhitelabel().isEnabled()) {
            result = result.switchIfEmpty(renderDefaultErrorView(responseBody, error));
        }
        else {
            Throwable ex = getError(request);
            result = result.switchIfEmpty(Mono.error(ex));
        }
        return result.next().doOnNext((response) -> logError(request, errorStatus));
    }

    /**
     * Render the error information as a JSON payload.
     * @param request the current request
     * @return a {@code Publisher} of the HTTP response
     */
    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
        Map<String, Object> error = getErrorAttributes(request, includeStackTrace);

        ResultResponse<String> resultResponse = ResultResponse.failed();
        logger.debug(error);

        HttpStatus errorStatus = getHttpStatus(error);
        return ServerResponse.status(getHttpStatus(error))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(resultResponse))
                .doOnNext((resp) -> logError(request, errorStatus));
    }

    protected void logError(ServerRequest request, HttpStatus errorStatus) {
        Throwable ex = getError(request);
        log(request, ex, (errorStatus.is5xxServerError() ? logger::error : logger::warn));
    }

    private void log(ServerRequest request, Throwable ex,
                     BiConsumer<Object, Throwable> logger) {
        if (ex instanceof ResponseStatusException) {
            logger.accept(buildMessage(request, ex), null);
        }
        else {
            logger.accept(buildMessage(request, null), ex);
        }
    }

    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("Bkhech Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

}
