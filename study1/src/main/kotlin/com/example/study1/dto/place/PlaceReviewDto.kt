package com.example.study1.dto.users

import com.example.study1.entity.users.PlaceReviewEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Schema(description = "장소")
data class PlaceReviewDto(
    @JsonIgnore
    @Schema(description = "리뷰 id")
    var id: Long?,

    @Schema(description = "장소 id")
    var placeId: Long,

    @NotNull(message = "title cannot be null")
    @Size(min = 2, message = "title not be less than two characters")
    @Schema(description = "제목", example = "개좋음")
    var title: String,

    @NotNull(message = "content cannot be null")
    @Size(min = 2, message = "content not be less than two characters")
    @Schema(description = "내용", example = "오늘 크로스핏메이커스 처음 이용해봤는데 코치님이 친절하시고 처음인데 자세도 잘 봐주셔서 좋았습니다!!\n" +
            "역도 동작은 처음해봤는데 안전하고 재밌게 배울 수 있는게 장점인것 같아요ㅎㅎ\n" +
            "크로스핏 초보자에게 꼭 추천합니다☺\uFE0F\uD83D\uDC4D\uD83D\uDC4D")
    var content: String,

    @NotNull(message = "lng cannot be null")
    @Schema(description = "리뷰 별점", example = "5.0")
    @DecimalMax(value = "5.0", message = "최대 값은 5.0입니다.")
    @DecimalMin(value = "0.5", message = "최소 값은 0.5입니다.")
    var star: Float,

    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonIgnore
    var createId: String? = "test",

    @JsonIgnore
    var updatedAt: LocalDateTime? = LocalDateTime.now(),

    @JsonIgnore
    var updateId: String?,
) {
    fun toEntity(): PlaceReviewEntity {
        if(id != null)
            return PlaceReviewEntity(
                id = id!!,
                placeId = placeId,
                title = title,
                content = content,
                star = star,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
        else
            return PlaceReviewEntity(
                placeId = placeId,
                title = title,
                content = content,
                star = star,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
    }

    companion object {
        fun fromEntity(placeReviewEntity: PlaceReviewEntity): PlaceReviewDto {
            return placeReviewEntity.run {
                PlaceReviewDto(
                    id = id!!,
                    placeId = placeId,
                    title = title,
                    content = content,
                    star = star,
                    createdAt = createdAt,
                    createId = createId!!,
                    updatedAt = updatedAt,
                    updateId = updateId
                )
            }
        }
    }
}