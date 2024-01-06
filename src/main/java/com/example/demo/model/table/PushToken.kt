package com.example.demo.model.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "push_token")
class PushToken(
    @Id
    @Column(name = "token", unique = true)
    var token: String = "",
    @Column(name = "device_uid")
    var deviceUid: String = "",
    @Column(name = "user_id")
    var userId: Int = 0,
)

