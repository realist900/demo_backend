package com.example.demo.model.table

import jakarta.persistence.*

@Entity
@Table(name = "usr")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    @Column(unique = true)
    var login: String = "",
    var password: String = "",
    var name: String? = null,
    var email: String? = null,
    var isActive: Boolean = false,
)

