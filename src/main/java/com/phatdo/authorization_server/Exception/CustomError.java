package com.phatdo.authorization_server.Exception;

public enum CustomError {
    USEREXIST("Unique user constraint has been violated", 500);

    private String message;
    private int code;

    private CustomError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
