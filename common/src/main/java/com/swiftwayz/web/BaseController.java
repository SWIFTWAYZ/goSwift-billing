package com.swiftwayz.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Base of all controllers
 * Created by sydney on 2017/04/16.
 */
public abstract  class BaseController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected ResponseEntity<Object> httpOk() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<String> httpBadRequest(String message) {
        LOG.error(message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<String> httpBadRequest(Exception exception) {
        LOG.error("Bad Request", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<?> httpOk(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
