package com.example.demo.service

import com.example.demo.repository.PushTokenRepository
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class PushNotificationService(
    private val pushTokenRepository: PushTokenRepository,
    private val firebaseMessaging: FirebaseMessaging,
) {
    fun sendDeleteOldCatsNotification(userId: Int) {
        val tokens = pushTokenRepository.findAllByUserId(userId)
        if (tokens.isEmpty()) return

        val notification = Notification.builder()
            .setTitle("Внимание!")
            .setBody("Ваши коты удалены")
            .build()

        tokens.forEach {
            val message = Message.builder()
                .setToken(it.token)
                .setNotification(notification)
                .build()

            try {
                firebaseMessaging.send(message)
            } catch (e: FirebaseMessagingException) {
                e.printStackTrace()
            }
        }
    }
}