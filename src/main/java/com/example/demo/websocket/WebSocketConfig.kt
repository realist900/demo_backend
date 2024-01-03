package com.example.demo.websocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
open class WebSocketConfig(
    private val webSocketHandler: WebSocketHandler,
    private val webSocketHandshakeInterceptor: WebSocketHandshakeInterceptor
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler, "/websocket")
            .addInterceptors(webSocketHandshakeInterceptor)
    }

    @Bean
    open fun myWebSocketHandler(): WebSocketHandler {
        return WebSocketHandler()
    }
}