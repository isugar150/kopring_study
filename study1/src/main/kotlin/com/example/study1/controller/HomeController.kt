package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import com.example.study1.dto.users.UsersDto
import com.example.study1.entity.users.UsersEntity
import com.example.study1.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import jakarta.servlet.http.HttpServletRequest
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
    fun users(param: UsersDto, request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        if (request.method.equals("GET")) { // Read
            result.list = usersRepository.findAll()
            result.success = true
        } else if (request.method.equals("POST") || request.method.equals("PUT") || request.method.equals("PATCH")) { // Create, Update
            usersRepository.save(param.toEntity())
            result.success = true
        }else if (request.method.equals("DELETE")) { // Delete
            param.id?.let {
                usersRepository.deleteById(it)
                result.success = true
            }
        } else {
            result.message = "Method is not allow"
        }

        return ResponseEntity(result, HttpStatus.OK)
    }

}