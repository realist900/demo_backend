package com.example.demo.repository

import com.example.demo.model.Cat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CatRepository : CrudRepository<Cat, Int>