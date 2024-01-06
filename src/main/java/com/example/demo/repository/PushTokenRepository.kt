package com.example.demo.repository

import com.example.demo.model.table.PushToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PushTokenRepository : CrudRepository<PushToken, Int> {

    fun findByToken(token: String): PushToken?

    fun findAllByUserId(userId: Int): List<PushToken>

    fun deleteByDeviceUidAndUserId(deviceUid: String, userId: Int)

}