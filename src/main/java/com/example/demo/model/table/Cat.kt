package com.example.demo.model.table

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cat")
class Cat(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var userId: Int = 0,
    var created: LocalDateTime? = null,
    var breed: String? = null,
    var picture: String? = null
)