package com.samgau.security;

public enum UserPermission {
    BORROW_BOOK("borrow:book"),
    RETURN_BOOK("borrow:return"),
    ALL_FUNCTIONALITIES("all:functionalities");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
