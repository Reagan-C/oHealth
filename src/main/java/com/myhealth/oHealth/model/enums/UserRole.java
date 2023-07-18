package com.myhealth.oHealth.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    USER("Regular"),
    ADMIN("Administrator"),
    SUPER_ADMIN("Super Administrator");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
