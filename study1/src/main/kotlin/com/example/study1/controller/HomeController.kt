package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping
    fun home(model: Model): ResponseEntity<RestResult> {
        var result = RestResult()

        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

}