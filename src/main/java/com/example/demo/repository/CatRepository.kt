package com.example.demo.repository

import com.example.demo.model.table.Cat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CatRepository : CrudRepository<Cat, Int> {
    fun findAllByUserId(userId: Int): Iterable<Cat>

    fun findByIdAndUserId(id: Int, userId: Int): Cat?

    fun existsCatByIdAndUserId(id: Int, userId: Int): Boolean
}