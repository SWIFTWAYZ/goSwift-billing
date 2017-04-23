package com.swiftwayz.domain.util;

/**
 * Created by sydney on 2017/04/17.
 */
public enum Status {

    ACTIVE("Active"),
    PENDING("PENDING"),
    IN_ACTIVE("InActive");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
