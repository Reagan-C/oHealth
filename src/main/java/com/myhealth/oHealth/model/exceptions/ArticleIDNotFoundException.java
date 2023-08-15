package com.myhealth.oHealth.model.exceptions;

import java.io.Serial;

public class ArticleIDNotFoundException extends OhealthException {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "Article";

    private final Object id;
    public ArticleIDNotFoundException(Object articleId) {
        this.id = articleId;
    }

    @Override
    public String getMessage() {
        return String.format("%s with this ID  %s cannot be found or does not exist in our record",
                ENTITY_NAME, id.toString());
    }
}
