package com.example.study1.dto.users

import com.example.study1.entity.users.UsersEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Schema(description = "사용자")
data class UsersDto(
    @JsonIgnore
    @Schema(description = "사용자 고유번호", nullable = false)
    var id: Long?,

    @Schema(description = "로그인 아이디", nullable = false, example = "test")
    var userId: String?,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password not be less than two characters")
    @Schema(description = "사용자 비밀번호", nullable = false, example = "test")
    var password: String?,

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    @Schema(description = "사용자 이름", nullable = false, example = "홍길동")
    var name: String?,

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    @Schema(description = "사용자 이메일", nullable = false, example = "hgd1414@gmail.com")
    var email: String?,

    @NotNull(message = "PhoneNumber cannot be null")
    @Schema(description = "사용자 핸드폰 번호", nullable = false, example = "010-1212-3131")
    var phone: String?,

    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonIgnore
    var createId: String? = "test",

    @JsonIgnore
    var updatedAt: LocalDateTime? = LocalDateTime.now(),

    @JsonIgnore
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