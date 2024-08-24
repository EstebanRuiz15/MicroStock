package com.microstock.apistock.domain.util;

public final class  ConstantsDomain {
    private ConstantsDomain(){
        throw new IllegalStateException("Utility class");
    }
    
    public static final String NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The name is already exists";
    public static final String NAME_MAX_CHARACTERS_EXCEPTION_MESSAGE  = "The name must be less than 50 characters";
    public static final String NAME_NOT_NULL_EXCEPTION_MESSAGE = "Name cannot be null";
    public static final String DESCRIPTION_MAX_CHARACTERS_EXCEPTION_MESSAGE = "The description must be less than 90 characters";
    public static final String DESCRIPTION_NOT_NULL_EXCEPTION_MESSAGE = "Description cannot be null";
    public static final Long NOT_NULL=1L;
    public static final Long MAX_NAME_CHARACTERS =50L;
    public static final Long MAX_DESCRIPTION_CHARACTERS =90L;
}
