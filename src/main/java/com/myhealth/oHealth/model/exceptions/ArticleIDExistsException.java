package com.myhealth.oHealth.model.exceptions;

import java.io.Serial;

public class ArticleIDExistsException extends OhealthException {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "Article";


    @Override
    public String getMessage() {
        return ENTITY_NAME + " already exists in our record";
    }
}
