package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import com.example.study1.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

class HomeController() {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @GetMapping("/")
    fun home(model: Model): ResponseEntity<RestResult> {
        var result = RestResult()

        result.success = true
        result.message = "welcome"

        return ResponseEntity(result, HttpStatus.OK)
    }

    @RequestMapping("/users")
    fun users(model: Model, request: HttpRequest): ResponseEntity<RestResult> {
        var result = RestResult()


        if (request.method == HttpMethod.GET) { // Read
            result.setData("list", usersRepository.findAll())
        } else if (request.method == HttpMethod.POST) { // Create

        } else if (request.method == HttpMethod.POST || request.method == HttpMethod.PATCH) { // Update

        } else if (request.method == HttpMethod.DELETE) { // Delete

        } else {
            result.message = "Method "
        }

        return ResponseEntity(result, HttpStatus.OK)
    }

}