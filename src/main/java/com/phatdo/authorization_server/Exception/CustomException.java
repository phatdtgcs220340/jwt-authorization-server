package com.phatdo.authorization_server.Exception;

public class CustomException extends Exception {
    private CustomError error;

    public CustomException(CustomError error) {
        super(error.getMessage());
    }

    public CustomError getError() {
        return this.error;
    }
}
