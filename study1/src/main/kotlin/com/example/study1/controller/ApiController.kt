package com.example.study1.controller

import com.example.study1.component.constant.HttpMethod
import com.example.study1.dto.common.RestResult
import com.example.study1.dto.users.UsersDto
import com.example.study1.repository.UsersRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["예제 API"], description = "Swagger 테스트용 API")
class ApiController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @ApiOperation(value = "문자열 반복", notes = "파라미터로 받은 문자열을 2번 반복합니다.")
    @ApiImplicitParams(
        ApiImplicitParam(name = "id", value = "사용자 고유 아이디"),
        ApiImplicitParam(name = "userId", value = "로그인 아이디"),
        ApiImplicitParam(name = "password", value = "비밀번호"),
        ApiImplicitParam(name = "name", value = "이름"),
        ApiImplicitParam(name = "phone", value = "전화번호"),
    )
    @GetMapping("/users")
    fun getUsers(param: UsersDto, request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        result.list = usersRepository.findAll()
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping("/users")
    fun postUsers(param: UsersDto, request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        usersRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }
}