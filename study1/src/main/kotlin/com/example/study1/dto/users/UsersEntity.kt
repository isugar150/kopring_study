package com.example.study1.dto.users

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UsersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    val userId: String,
    val password: String,
    val name: String,
    val phone: String?,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createId: String,
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    val updateId: String?
) {
}
