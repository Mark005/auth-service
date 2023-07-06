package com.bmo.common.auth_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorityEnum {
    SECURITY_USER_READ("security-user:read"),

    AUTHORITY_READ("authority:read"),

    USER_AUTHORITY_UPDATE("user-authority:update"),

    USER_READ("user:read"),

    CATEGORY_CREATE("category:create"),
    CATEGORY_UPDATE("category:update"),
    CATEGORY_DELETE("category:delete"),

    PRODUCT_ITEM_READ("product-item:read"),
    PRODUCT_ITEM_CREATE("product-item:create"),
    PRODUCT_ITEM_UPDATE("product-item:update"),
    PRODUCT_ITEM_DELETE("product-item:delete"),
    ;

    private final String stringAuthority;

    public static AuthorityEnum getByStringAuthority(String stringAuthority) {
        for (AuthorityEnum value : values()) {
            if (value.getStringAuthority().equals(stringAuthority)) {
                return value;
            }
        }
        throw new IllegalArgumentException("AuthorityEnum {%s} does not exists".formatted(stringAuthority));
    }
}
