package com.phatdo.authorization_server.Exception;

import lombok.Getter;

@Getter
public enum CustomError {
    USER_EXIST("Unique user constraint has been violated", 500);

    private final String message;
    private final int code;

    CustomError(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
