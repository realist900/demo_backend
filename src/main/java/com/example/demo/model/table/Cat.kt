package com.example.demo.model.table

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cat")
class Cat(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Int = 0,
    @Column(name = "user_id")
    var userId: Int = 0,
    @Column(name = "created")
    var created: LocalDateTime? = null,
    @Column(name = "breed")
    var breed: String? = null,
    @Column(name = "picture")
    var picture: String? = null
)