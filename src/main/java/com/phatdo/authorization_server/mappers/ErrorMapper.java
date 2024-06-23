package com.phatdo.authorization_server.mappers;

import com.phatdo.authorization_server.Exception.CustomException;
import com.phatdo.authorization_server.dto.response.ErrorDTO;

public class ErrorMapper {
    public static ErrorDTO toDto(CustomException exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
