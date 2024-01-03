package com.example.demo.repository

import com.example.demo.model.table.Cat
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CatRepository : CrudRepository<Cat, Int> {

    @Query(
        value = "SELECT * FROM cat WHERE user_id = :userId ORDER BY id LIMIT :limit OFFSET :offset",
        nativeQuery = true
    )
    fun findCatsWithLimitAndOffset(
        @Param("userId") userId: Int,
        @Param("limit") limit: Int,
        @Param("offset") offset: Int
    ): List<Cat>

    fun countCatsByUserId(userId: Int): Long

    fun findByIdAndUserId(id: Int, userId: Int): Cat?

    fun existsCatByIdAndUserId(id: Int, userId: Int): Boolean

    fun findAllByCreatedBefore(dateTime: LocalDateTime): List<Cat>


}