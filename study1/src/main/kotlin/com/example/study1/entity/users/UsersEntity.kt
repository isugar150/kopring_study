package com.example.study1.entity.users

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
open class UsersEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userIdnfr")
    open var id: Long = 0,

    @Column(length = 30)
    open val userId: String,
    @Column(length = 100)
    open val password: String,
    @Column(length = 30)
    open val name: String,
    @Column(length = 30, nullable = true)
    open val email: String?,
    @Column(length = 30, nullable = true)
    open val phone: String?,

    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val createId: String,
    @Column(nullable = true)
    open val updatedAt: LocalDateTime? = LocalDateTime.now(),
    @Column(nullable = true)
    open val updateId: String?
) { }
