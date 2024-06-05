package com.phatdo.authorization_server.Exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private CustomError error;

    public CustomException(CustomError error) {
        super(error.getMessage());
    }

}
