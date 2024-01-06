package com.example.demo.model.table

import jakarta.persistence.*

@Entity
@Table(name = "usr")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Int = 0,
    @Column(name = "login", unique = true)
    var login: String = "",
    @Column(name = "password")
    var password: String = "",
    @Column(name = "name")
    var name: String? = null,
    @Column(name = "email")
    var email: String? = null,
    @Column(name = "is_active")
    var isActive: Boolean = false,
)

