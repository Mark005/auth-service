package com.bmo.common.auth_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    SECURITY_USER_READ("security-user:read"),
    USER_READ("user:read")
    ;

    private final String stringAuthority;

    public static Authority getByStringAuthority(String stringAuthority) {
        for (Authority value : values()) {
            if (value.getStringAuthority().equals(stringAuthority)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Authority {%s} does not exists".formatted(stringAuthority));
    }
}
