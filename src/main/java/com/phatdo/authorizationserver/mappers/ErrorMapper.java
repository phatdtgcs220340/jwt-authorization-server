package com.phatdo.authorizationserver.mappers;

import com.phatdo.authorizationserver.exception.CustomException;
import com.phatdo.authorizationserver.dto.response.ErrorDTO;

public class ErrorMapper {
    public static ErrorDTO toDto(CustomException exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
