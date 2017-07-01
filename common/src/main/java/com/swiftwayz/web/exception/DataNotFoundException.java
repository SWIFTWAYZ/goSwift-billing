package com.swiftwayz.web.exception;

/**
 * Created by sydney on 6/11/17.
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String resource, String property) {
        super(String.format("%s \'%s\' does not exist.", new Object[]{resource, property}));
    }
}
