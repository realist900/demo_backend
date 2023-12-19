package com.example.demo.repository

import com.example.demo.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User?, Int?> {
    fun findByLogin(login: String): User?
}