package com.example.study1.controller

import com.example.study1.component.constant.HttpMethod
import com.example.study1.dto.common.RestResult
import com.example.study1.dto.users.UsersDto
import com.example.study1.repository.UsersRepository
import io.swagger.annotations.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(value = "예제 API", tags = ["example"], description = "Swagger 테스트용 API")
class ApiController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Operation(summary = "사용자 리스트", description = "사용자 리스트를 조회합니다.", tags = ["사용자"])
    @GetMapping(value = ["/users"])
    fun getUsers(request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        result.list = usersRepository.findAll()
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(value = ["/users"])
    fun postUsers(param: UsersDto, request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        usersRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }
}