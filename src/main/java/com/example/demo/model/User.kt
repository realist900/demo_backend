package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "usr")
class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var login: String = "",
        var password: String = "",
        var name: String? = null,
        var email: String? = null,
        var isActive: Boolean = false)