package com.example.demo.service

import com.example.demo.model.common.BaseEvent
import com.example.demo.repository.CatRepository
import com.example.demo.websocket.WebSocketHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class CatService(
    private val catRepository: CatRepository,
    private val pushNotificationService: PushNotificationService,
    private val webSocketHandler: WebSocketHandler,
    private val objectMapper: ObjectMapper
) {
    fun deleteOldCats() {
        val oneDayAgo = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC).minusMinutes(1)
        val oldCats = catRepository.findAllByCreatedBefore(oneDayAgo)
        val mapByUserId = oldCats.groupBy { it.userId }

        catRepository.deleteAll(oldCats)

        mapByUserId.forEach { (userId, cats) ->
            val event = BaseEvent(type = "removeCats", payload = cats.map { it.id })
            webSocketHandler.sendMessage(userId, objectMapper.writeValueAsString(event))
            pushNotificationService.sendDeleteOldCatsNotification(userId)
        }
    }
}