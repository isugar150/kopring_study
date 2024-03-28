package com.example.study1.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.QueryByExampleExecutor

@NoRepositoryBean
interface JpaRepository<T, ID> : PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    fun findAll(): List<T>?

    fun findAllById(ids: Iterable<ID>?): List<T>?

    fun <S : T?> saveAll(entities: Iterable<S>?): List<S>?

    fun flush()

    fun deleteAllInBatch()

    fun getOne(id: ID): T

}