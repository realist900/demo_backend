package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "cat")
class Cat(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = 0,
        var breed: String? = null,
        var picture: String? = null)