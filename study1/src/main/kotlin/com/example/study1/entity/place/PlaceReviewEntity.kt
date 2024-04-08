package com.example.study1.entity.place

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import java.time.LocalDateTime

@Entity
@Table(name = "placeReview")
open class PlaceReviewEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeReviewId")
    open var id: Long = 0,

    open var placeId: Long = 0,

    @Column(length = 30)
    open val title: String,

    @Column(length = 10000)
    open val content: String,

    @DecimalMax(value = "5.0", message = "최대 값은 5.0입니다.")
    @DecimalMin(value = "0.5", message = "최소 값은 0.5입니다.")
    open val star: Float,

    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val createId: String,

    @Column(nullable = true)
    open val updatedAt: LocalDateTime? = LocalDateTime.now(),
    @Column(nullable = true)
    open val updateId: String?
) { }
