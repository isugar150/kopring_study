package com.example.study1.repository

import com.example.study1.entity.place.PlaceEntity
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : JpaRepository<PlaceEntity, Long> {
    override fun findAll(): List<PlaceEntity>?
    fun findById(placeId: Long): PlaceEntity?
    fun findByNameAndLatAndLng(name: String?, lat: String?, lng: String?): PlaceEntity?
    override fun <E : PlaceEntity?> save(entity: E): E
    override fun deleteById(id: Long)
}