package com.phatdo.authorizationserver.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomError {
    USER_EXIST("Unique user constraint has been violated", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;

    CustomError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }

}
