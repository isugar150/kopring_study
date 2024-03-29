package com.example.study1.entity.users

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
open class UsersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userIdnfr")
    var id: Long = 0,

    @Column(length = 30)
    val userId: String,
    @Column(length = 100)
    val password: String,
    @Column(length = 30)
    val name: String,
    @Column(length = 30, nullable = true)
    val email: String?,
    @Column(length = 30, nullable = true)
    val phone: String?,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createId: String,
    @Column(nullable = true)
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    @Column(nullable = true)
    val updateId: String?
) { }
