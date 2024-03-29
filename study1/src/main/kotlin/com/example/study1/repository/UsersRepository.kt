package com.example.study1.repository

import com.example.study1.entity.users.UsersEntity
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<UsersEntity, Long> {
    override fun findAll(): List<UsersEntity>?
    override fun <E : UsersEntity?> save(entity: E): E
    override fun deleteById(id: Long)
}