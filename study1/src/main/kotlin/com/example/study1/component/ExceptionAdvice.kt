package com.example.study1.component

import com.example.study1.dto.common.RestResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import java.util.logging.Logger

@RestControllerAdvice
class ExceptionAdvice {

    val logger = Logger.getLogger(ExceptionAdvice::class.java.name)

    @ExceptionHandler(NoHandlerFoundException::class)
    fun exceptionAdvice(e: NoHandlerFoundException) : ResponseEntity<RestResult> {
        logger.warning(StringUtils().getStackTrace(e))
        var result = RestResult()
        result.message = "페이지를 찾을 수 없습니다."
        return ResponseEntity(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception::class)
    fun exceptionAdvice(e: Exception) : ResponseEntity<RestResult> {
        logger.warning(StringUtils().getStackTrace(e))
        var result = RestResult()
        result.message = "서버에서 오류가 발생하였습니다."
        return ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}