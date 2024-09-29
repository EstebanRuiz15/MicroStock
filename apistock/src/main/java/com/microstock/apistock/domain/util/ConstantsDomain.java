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
    public static final String DESCRIPTION_MAX_CHARACTERS_BRAND_EXCEPTION_MESSAGE = "The description must be less than 120 characters";
    public static final Long NOT_NULL=1L;
    public static final Long MAX_NAME_CHARACTERS =50L;
    public static final Long MAX_DESCRIPTION_CHARACTERS =90L;
    public static final Long MAX_DESCRIPTION_BRAND_CHARACTERS =120L;
    public static final Integer ONE =1;
    public static final String ASC="asc";
    public static final String DESC="desc";
    public static final String ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE="The order should only by 'asc' or 'desc'";
    public static final Integer ZERO=0;
    public static final String PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE="The number of pages cannot be negative";
    public static final String SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE="The size number must be greater than 0";
    public static final String NO_CATEGORIES_FOUND_EXCEPTION_MESSAGE="NO categories were found for this page";
    public static final String NO_BRANDS_FOUND_EXCEPTION_MESSAGE="NO brands were found for this page";
    public static final String NO_DATA_BRANDS_EXCEPTION_MESSAGE="No brand data";
    public static final String NO_DATA_CATEGORY_EXCEPTION_MESSAGE="No category data";
    public static final String TOTAL_PAGES=", total pages is: ";
    public static final String ELEMENTS_REPEATS_MESSAGE="The list cannot have repeated elements";
    public static final String ERROR_VALIDATION="Error of validation";
    public static final String NO_CATEGORY_EXCEPTION_MESSAGE="Category not found";
    public static final String NO_BRAND_EXCEPTION_MESSAGE="Brand not found";
    public static final String ORDEN_NAME_DIFERENT_EXCEPTION_MESSAGE="The order name should only by 'category', 'brand' or 'article'";
    public static final String ARTICLE="article";
    public static final String BRAND="brand";
    public static final String CATEGORY="category";
    public static final String NO_DATA_ARTICLE_EXCEPTION_MESSAGE="No article data";
    public static final String NO_ARTICLES_FOUND_EXCEPTION_MESSAGE="NO articles were found for this page";
    public static final String NO_ARTICLE_FOUND="Article not found";
    public static final Integer TWO=2;
    public static final String QUANTITY_INSIFICENT="Insufficient quantity in stock for item with id: ";
    public static final String STOCK_AVAILABLE=". Stock available: ";
}
