package com.example.study1.repository

import com.example.study1.dto.users.PlaceReviewDto
import com.example.study1.entity.users.PlaceReviewEntity
import org.springframework.stereotype.Repository

@Repository
interface PlaceReviewRepository : JpaRepository<PlaceReviewEntity, Long> {
    override fun findAll(): List<PlaceReviewEntity>
    fun findAllByPlaceId(placeId: Long): List<PlaceReviewEntity>
    fun findByPlaceId(placeId: Long): List<PlaceReviewDto>?
    override fun <E : PlaceReviewEntity?> save(entity: E): E
    override fun deleteById(id: Long)
}