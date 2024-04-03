package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import com.example.study1.dto.users.PlaceDto
import com.example.study1.dto.users.UsersDto
import com.example.study1.repository.PlaceRepository
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
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController {

    @Autowired
    lateinit var placeRepository: PlaceRepository

    @Operation(summary = "장소 리스트", description = "장소 리스트를 조회합니다.", tags = ["장소"])
    @GetMapping(value = ["/places"])
    fun getPlaces(request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        result.list = placeRepository.findAll()
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

    @Operation(summary = "장소 저장", description = "장소 정보를 추가합니다.", tags = ["장소"])
    @PostMapping(value = ["/places"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun postPlaces(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                        content = [
                            Content(schema = Schema(implementation = PlaceDto::class))
                        ]
                    )@Valid param: PlaceDto
                  , request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        val dto: PlaceDto? = placeRepository.findByNameAndLatAndLng(param.name, param.lat, param.lng)

        if(dto != null) {
            result.message = "Duplicate place"
            return ResponseEntity(result, HttpStatus.BAD_REQUEST)
        }

        placeRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }
}