package com.example.study1.dto.users

import com.example.study1.entity.users.PlaceEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Schema(description = "장소")
data class PlaceDto(
    @JsonIgnore
    @Schema(description = "장소 id")
    var id: Long?,

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    @Schema(description = "장소명", example = "크로스핏메이커스")
    var name: String?,

    @NotNull(message = "price cannot be null")
    @Size(min = 2, message = "price not be less than two characters")
    @Schema(description = "가격", example = "200000000")
    var price: Int?,

    @NotNull(message = "lat cannot be null")
    @Schema(description = "좌표 경도", example = "14146626.095153")
    var lat: String,

    @NotNull(message = "lng cannot be null")
    @Schema(description = "좌표 위도", example = "4517125.8073948")
    var lng: String,

    @Schema(description = "오픈 시간", example = "07:00")
    var startDate: LocalDateTime?,

    @Schema(description = "마감 시간", example = "22:00")
    var endDate: LocalDateTime?,

    @Schema(hidden = true)
    var starAvg: Float = 0.0F,

    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonIgnore
    var createId: String? = "test",

    @JsonIgnore
    var updatedAt: LocalDateTime? = LocalDateTime.now(),

    @JsonIgnore
    var updateId: String?,
) {
    fun toEntity(): PlaceEntity {
        if(id != null)
            return PlaceEntity(
                id = id!!,
                name = name!!,
                price = price,
                lat = lat,
                lng = lng,
                startDate = startDate,
                endDate = endDate,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
        else
            return PlaceEntity(
                name = name!!,
                price = price,
                lat = lat,
                lng = lng,
                startDate = startDate,
                endDate = endDate,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
    }

    companion object {
        fun fromEntity(placeEntity: PlaceEntity): PlaceDto {
            return placeEntity.run {
                PlaceDto(
                    id = id!!,
                    name = name!!,
                    price = price,
                    lat = lat,
                    lng = lng,
                    startDate = startDate,
                    endDate = endDate,
                    createdAt = createdAt,
                    createId = createId!!,
                    updatedAt = updatedAt,
                    updateId = updateId
                )
            }
        }
    }
}