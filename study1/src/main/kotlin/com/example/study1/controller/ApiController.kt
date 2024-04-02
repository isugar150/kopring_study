package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import com.example.study1.dto.users.UsersDto
import com.example.study1.repository.UsersRepository
import io.swagger.annotations.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @Operation(summary = "사용자 저장", description = "사용자 정보를 추가합니다.", tags = ["사용자"])
    @PostMapping(value = ["/users"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun postUsers(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                        content = [
                            Content(schema = Schema(implementation = UsersDto::class))
                        ]
                    )@Valid param: UsersDto
                  , request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        val dto: UsersDto? = usersRepository.findByUserId(param.userId)

        if(dto != null) {
            result.message = "Duplicate userId"
            return ResponseEntity(result, HttpStatus.BAD_REQUEST)
        }

        usersRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }
}