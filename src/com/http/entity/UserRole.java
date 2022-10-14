package com.http.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    USER,
    ADMIN;

    public static Optional<UserRole> find(String role) {
        return Arrays.stream(values())
                     .filter(it -> it.name()
                                     .equals(role))
                     .findFirst();
    }
}
