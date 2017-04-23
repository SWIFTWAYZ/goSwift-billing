package com.swiftwayz.domain.util;

/**
 * Created by sydney on 2017/04/23.
 */
public class ModelMapper {

    private static final ModelMapper INSTANCE = new ModelMapper();
    private static final org.modelmapper.ModelMapper MAPPER = new org.modelmapper.ModelMapper();

    private ModelMapper() {
    }

    public static org.modelmapper.ModelMapper getMapper() {
        return MAPPER;
    }
}
