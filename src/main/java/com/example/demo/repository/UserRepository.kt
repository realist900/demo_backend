package com.example.demo.repository

import com.example.demo.model.table.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User?, Int?> {
    fun findByLogin(login: String): User?
}