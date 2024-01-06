package com.example.demo.websocket

import com.example.demo.common.CommonUtil
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap


@Component
class WebSocketHandler : TextWebSocketHandler() {

    private val sessions = ConcurrentHashMap<Int, WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val userId = session.attributes["userId"] as Int

        sessions[userId] = session
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        sessions.remove(CommonUtil.getUserId())
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Received message: ${message.payload}")
        session.sendMessage(TextMessage("message from server"))
    }

    fun sendMessage(userId: Int, message: String) {
        sessions[userId]?.sendMessage(TextMessage(message))
    }
}