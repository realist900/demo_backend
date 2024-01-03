package com.example.demo.websocket

import com.example.demo.common.JwtUtil
import com.example.demo.service.CustomUserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.socket.server.HandshakeInterceptor

@Component
class WebSocketHandshakeInterceptor(
    private val jwtUtil: JwtUtil,
    private val customUserDetailsService: CustomUserDetailsService
) : HandshakeInterceptor {

    private fun extractJwtFromRequest(request: ServerHttpRequest): String? {
        val headers = request.headers["Authorization"] ?: return null
        val authHeader = headers.firstOrNull() ?: return null
        if (!authHeader.startsWith("Bearer ")) {
            return null
        }
        return authHeader.substring(7)
    }

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: org.springframework.web.socket.WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        val token = extractJwtFromRequest(request)
        if (token != null) {
            val username = jwtUtil.extractUsername(token)
            val userDetails =
                customUserDetailsService.loadUserByUsername(username)
            if (jwtUtil.validateToken(token, userDetails)) {
                val auth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                SecurityContextHolder.getContext().authentication = auth
                attributes["userId"] = userDetails.user.id
                return true
            }
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED)
        return false
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: org.springframework.web.socket.WebSocketHandler,
        exception: java.lang.Exception?
    ) {

    }
}