package com.example.study1.component

import com.example.study1.dto.common.RestResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    @ExceptionHandler(Exception::class)
    fun boardNotFoundExceptionAdvice() : ResponseEntity<RestResult> {
        var result = RestResult()
        result.message = "서버에서 오류가 발생하였습니다."
        return ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}