package com.example.study1.controller

import com.example.study1.dto.common.RestResult
import com.example.study1.dto.place.PlaceDto
import com.example.study1.dto.place.PlaceReviewDto
import com.example.study1.entity.place.PlaceEntity
import com.example.study1.repository.PlaceRepository
import com.example.study1.repository.PlaceReviewRepository
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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.round

@RestController
class PlaceController {

    @Autowired
    lateinit var placeRepository: PlaceRepository

    @Autowired
    lateinit var placeReviewRepository: PlaceReviewRepository

    @Operation(summary = "장소 목록", description = "장소 목록를 조회합니다.", tags = ["장소"])
    @GetMapping(value = ["/places"])
    fun getPlaces(request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()


        val list = placeRepository.findAll()?.map { obj ->
            val dto = PlaceDto.fromEntity(obj)
            // 해당 장소의 리뷰 점수 평균을 구한다
            val reviewList = placeReviewRepository.findAllByPlaceId(obj.id).map { reviewObj -> PlaceReviewDto.fromEntity(reviewObj) }
            var addNum = 0.0F
            if(reviewList.size > 0) {
                for (star in reviewList) {
                    addNum += star.star
                }
                dto.starAvg = round(addNum / reviewList.size*100) / 100
            }
            dto.starCnt = reviewList.size
            dto
        }

        val data: HashMap<String, Any> = HashMap()
        list?.let { data.put("list", it) }

        result.data = data
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

    @Operation(summary = "장소 리뷰 목록", description = "장소 리뷰 목록을 조회합니다.", tags = ["장소"])
    @GetMapping(value = ["/places/review/{placeId}"])
    fun getPlacesReview(@PathVariable placeId: Long, request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        val list = placeReviewRepository.findAllByPlaceId(placeId)

        val data: HashMap<String, Any> = HashMap()
        list?.let { data.put("list", it) }

        result.data = data
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

        val dto: PlaceEntity? = placeRepository.findByNameAndLatAndLng(param.name, param.lat, param.lng)

        if(dto != null) {
            result.message = "Duplicate place"
            return ResponseEntity(result, HttpStatus.BAD_REQUEST)
        }

        placeRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

    @Operation(summary = "장소 리뷰 작성", description = "장소 리뷰를 작성합니다.", tags = ["장소"])
    @PostMapping(value = ["/places/review"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun postPlacesReview(@io.swagger.v3.oas.annotations.parameters.RequestBody(
                        content = [
                            Content(schema = Schema(implementation = PlaceReviewDto::class))
                        ]
                    )@Valid param: PlaceReviewDto
                  , request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        val dto: PlaceEntity? = placeRepository.findById(param.placeId)

        if(dto == null) {
            result.message = "placeId does not exist"
            return ResponseEntity(result, HttpStatus.BAD_REQUEST)
        }

        placeReviewRepository.save(param.toEntity())
        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }
}