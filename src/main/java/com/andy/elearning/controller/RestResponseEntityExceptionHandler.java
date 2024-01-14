package com.andy.elearning.controller;

import com.andy.elearning.dto.ErrorDto;
import com.andy.elearning.enums.ErrorEnum;
import com.andy.elearning.exeptions.UsernameHasAlreadyExistedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {UsernameHasAlreadyExistedException.class})
    public ResponseEntity<?> handleUsernameExists(UsernameHasAlreadyExistedException uhaee) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(ErrorEnum.USER_HAS_ALREADY_EXISTED.getValue());
        errorDto.setMessage(uhaee.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }
}
