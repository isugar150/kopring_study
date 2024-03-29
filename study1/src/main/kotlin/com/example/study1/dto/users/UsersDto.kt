package com.example.study1.dto.users

import com.example.study1.entity.users.UsersEntity
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Schema(description = "사용자")
data class UsersDto(
    @Schema(description = "사용자 이메일", nullable = false, example = "k12@gmail.com")
    var id: Long?,

    @ApiModelProperty(value = "로그인 ID")
    var userId: String?,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password not be less than two characters")
    @Schema(description = "사용자 비밀번호", nullable = false, example = "pwd")
    @ApiModelProperty(value = "비밀번호")
    var password: String?,

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    @Schema(description = "사용자 이름", nullable = false, example = "김재한")
    var name: String?,

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    @Schema(description = "사용자 이메일", nullable = false, example = "k12@gmail.com")
    var email: String?,

    @NotNull(message = "PhoneNumber cannot be null")
    @Schema(description = "사용자 핸드폰 번호", nullable = false, example = "010-1212-3131")
    var phone: String?,

    @ApiModelProperty(value = "생성날짜")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @ApiModelProperty(value = "생성자")
    var createId: String?,

    @ApiModelProperty(value = "수정날짜")
    var updatedAt: LocalDateTime? = LocalDateTime.now(),

    @ApiModelProperty(value = "수정자")
    var updateId: String?
) {
    fun toEntity(): UsersEntity {
        if(id != null)
            return UsersEntity(
                id = id!!,
                userId = userId!!,
                password = password!!,
                name = name!!,
                email = email,
                phone = phone,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
        else
            return UsersEntity(
                userId = userId!!,
                password = password!!,
                name = name!!,
                email = email,
                phone = phone,
                createdAt = createdAt,
                createId = createId!!,
                updatedAt = updatedAt,
                updateId = updateId
            )
    }

    companion object {
        fun fromEntity(usersEntity: UsersEntity): UsersDto {
            return usersEntity.run {
                UsersDto(
                    id = id,
                    userId = userId!!,
                    password = password!!,
                    name = name!!,
                    email = email,
                    phone = phone,
                    createdAt = createdAt,
                    createId = createId!!,
                    updatedAt = updatedAt,
                    updateId = updateId
                )
            }
        }
    }
}