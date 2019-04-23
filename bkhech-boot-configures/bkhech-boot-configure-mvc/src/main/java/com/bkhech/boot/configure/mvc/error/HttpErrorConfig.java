package com.bkhech.boot.configure.mvc.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.bkhech.boot.configure.common.constants.ConfigureConstants.ERROR_PATH;
import static com.bkhech.boot.configure.common.constants.ConfigureConstants.NOT_FOUND_PATH;


/**
 * Created by guowm on 2017/4/6.
 */
@Component
public class HttpErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[3];
        errorPages[0] = new ErrorPage(HttpStatus.BAD_REQUEST, NOT_FOUND_PATH);
        errorPages[1] = new ErrorPage(HttpStatus.NOT_FOUND, NOT_FOUND_PATH);
        errorPages[2] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PATH);
        registry.addErrorPages(errorPages);
    }

}
