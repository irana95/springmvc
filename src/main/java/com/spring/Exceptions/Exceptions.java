package com.spring.Exceptions;

public enum Exceptions {

    USER_NOT_FOUND (100, "User not found "),
    USER_EXIST_WIDTH_ID (100, "User  already exists width id : "),
    USER_EXIST_WIDTH_MAIL (100, "User already exists width mail : "),
    USER_EXIST(101, "This user already exists width this mail.");

    private final int code;
    private  final String description;

    private Exceptions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
