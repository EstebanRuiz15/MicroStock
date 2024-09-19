package com.microstock.apistock.infraestructur.util;

public final  class ConstantsInfraestructure {
    
    private ConstantsInfraestructure(){
        throw new IllegalStateException("Utility class");
    }

    public static final String ERROR_CATEGORY = "Error creating category";
    public static final String ERROR_NAME_NULL = "the name cannot be null";
    public static final String ERROR_DESCRIPTION_NULL = "The description cannot be null";
    public static final String ERROR_PRICE="The price must be a positive number";
    public static final String ERROR_QUANTITY = "The quantity must be zero or greater";
    public static final String ERROR_CATEGORIES_1TO3 = "There must be between 1 to 3 categories";
    public static final int ONE=1;
    public static final int THREE=3;
    public static final String ERROR_BRAND = "The brands canbot be null";
    public static final String ACCES_DENIED_MESSAGE="{\\\"error\\\": \\\"Access Denied: ";
    public static final String KEY="294A404E635266556A586E327235753878214125442A472D4B6150645367566B";
    public static final String AUTHORIZATION="Authorization";
    public static final String BEARER="Bearer ";
    public static final String UPDATE="Article update";

}
