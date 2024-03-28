package com.example.study1.repository

import com.example.study1.dto.users.UsersEntity
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository : JpaRepository<UsersEntity, Long> {
    override fun findAll(): List<UsersEntity>?
}