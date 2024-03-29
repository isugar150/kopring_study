package com.example.study1.dto.users

import com.example.study1.entity.users.UsersEntity
import java.time.LocalDateTime

data class UsersDto(
    var id: Long?,
    var userId: String?,
    var password: String?,
    var name: String?,
    var phone: String?,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var createId: String?,
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
    var updateId: String?
) {
    fun toEntity(): UsersEntity {
        if(id != null)
            return UsersEntity(
                id = id!!,
                userId = userId!!,
                password = password!!,
                name = name!!,
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