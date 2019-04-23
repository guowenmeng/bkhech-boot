package com.bkhech.boot.configure.reactive.webflux;

import com.bkhech.boot.configure.common.dto.ResultResponse;
import com.bkhech.boot.configure.common.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * Created by guowm on 2018/4/20.
 */
@RestControllerAdvice
//@Component
public class ExceptionHandlers extends ResponseStatusExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResultResponse> handler(Exception ex) {
        return Mono.create(sink -> {
            if (ex instanceof SystemException) {
                SystemException exception = (SystemException) ex;
                sink.success(ResultResponse.custom(Integer.valueOf(exception.getCode()), exception.getMessage()));
            } else {
                sink.success(ResultResponse.failed(ex.getMessage()));
            }
        });
    }

//    private static final Log logger = LogFactory.getLog(ExceptionHandlers.class);
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        HttpStatus status = resolveStatus(ex);
//        if (status != null && exchange.getResponse().setStatusCode(status)) {
//            if (status.is5xxServerError()) {
//                logger.error(buildMessage(exchange.getRequest(), ex));
//            }
//            else if (status == HttpStatus.BAD_REQUEST) {
//                logger.warn(buildMessage(exchange.getRequest(), ex));
//            }
//            else {
//                logger.trace(buildMessage(exchange.getRequest(), ex));
//            }
//            return exchange.getResponse().setComplete();
//        }
//        return Mono.error(ex);
//    }
//
//    private String buildMessage(ServerHttpRequest request, Throwable ex) {
//        return "Kingnet Failed to handle request [" + request.getMethod() + " " + request.getURI() + "]: " + ex.getMessage();
//    }
//
//    @Nullable
//    private HttpStatus resolveStatus(Throwable ex) {
//        HttpStatus status = determineStatus(ex);
//        if (status == null) {
//            Throwable cause = ex.getCause();
//            if (cause != null) {
//                status = resolveStatus(cause);
//            }
//        }
//        return status;
//    }
//
//    /**
//     * Determine the HTTP status implied by the given exception.
//     * @param ex the exception to introspect
//     * @return the associated HTTP status, if any
//     * @since 5.0.5
//     */
//    @Nullable
//    protected HttpStatus determineStatus(Throwable ex) {
//        if (ex instanceof ResponseStatusException) {
//            return ((ResponseStatusException) ex).getStatus();
//        }
//        return null;
//    }

}
