package com.example.study1.repository

import jakarta.transaction.Transactional
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.QueryByExampleExecutor

@NoRepositoryBean
interface JpaRepository<T, ID> : PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    fun findAll(): List<T>?

    fun findAllById(ids: Iterable<ID>?): List<T>?

    @Transactional
    fun <E : T?> save(entity: E): E

    @Transactional
    fun <S : T?> saveAll(entities: Iterable<S>?): List<S>?

    @Transactional
    fun flush()

    @Transactional
    fun deleteById(id: ID)

    @Transactional
    fun deleteAllInBatch()

    fun getOne(id: ID): T

}