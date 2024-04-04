package com.example.study1.entity.users

import jakarta.persistence.*
import java.sql.Time
import java.time.LocalDateTime
import java.util.Date

@Entity
@Table(name = "place")
open class PlaceEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeId")
    open var id: Long = 0,

    @Column(length = 30)
    open val name: String,

    @Column(length = 30, nullable = true)
    open val lat: String,
    @Column(length = 30, nullable = true)
    open val lng: String,

    @Column(length = 30, nullable = true)
    open val price: Int?,

    @Column(length = 30, nullable = true)
    open val startDate: LocalDateTime?,
    @Column(length = 30, nullable = true)
    open val endDate: LocalDateTime?,

    @OneToMany(targetEntity = PlaceReviewEntity::class)
    @JoinColumn(name = "placeId")
    open val reviews: Collection<PlaceReviewEntity>?,

    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val createId: String,
    @Column(nullable = true)
    open val updatedAt: LocalDateTime? = LocalDateTime.now(),
    @Column(nullable = true)
    open val updateId: String?
) { }
