package com.example.study1.repository

import com.example.study1.dto.users.PlaceDto
import com.example.study1.dto.users.UsersDto
import com.example.study1.entity.users.PlaceEntity
import com.example.study1.entity.users.UsersEntity
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : JpaRepository<PlaceEntity, Long> {
    override fun findAll(): List<PlaceEntity>?
    fun findById(placeId: Long): PlaceEntity?
    fun findByNameAndLatAndLng(name: String?, lat: String?, lng: String?): PlaceDto?
    override fun <E : PlaceEntity?> save(entity: E): E
    override fun deleteById(id: Long)
}