package com.swiftwayz.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by sydney on 2017/04/16.
 */
public abstract  class BaseController {

    protected ResponseEntity<Object> httpOk() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<String> httpBadRequest(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    protected ResponseEntity<?> httpOk(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

}
