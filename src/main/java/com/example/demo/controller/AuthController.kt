package com.example.demo.controller

import com.example.demo.common.JwtUtil
import com.example.demo.model.CustomUserDetails
import com.example.demo.model.User
import com.example.demo.model.dto.TokenDto
import com.example.demo.model.dto.UserDto
import com.example.demo.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val userRepository: UserRepository,
                     private val passwordEncoder: PasswordEncoder,
                     private val jwtUtil: JwtUtil,
                     private val userDetailsService: UserDetailsService) {
    @PostMapping("/register")
    fun registerUser(@RequestBody user: User): ResponseEntity<Void> {
        user.password = passwordEncoder.encode(user.password)
        user.isActive = true
        userRepository.save(user)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody user: User): ResponseEntity<TokenDto> {
        val existingUser: User = userRepository.findByLogin(user.login)
                ?: throw RuntimeException("User not found")
        if (!passwordEncoder.matches(user.password, existingUser.password)) {
            throw BadCredentialsException("Incorrect password")
        }
        val jwt = jwtUtil.generateToken(CustomUserDetails(user))

        return ResponseEntity.ok(TokenDto(jwt))
    }

    @GetMapping("/user")
    fun getCurrentUser(@RequestHeader("Authorization") token: String): ResponseEntity<UserDto> {
        val username = jwtUtil.extractUsername(token.substring(7))
        val user: User? = userRepository.findByLogin(username)

        return if (user != null) {
            ResponseEntity.ok(UserDto(user.name, user.email))
        } else {
            ResponseEntity.notFound().build()
        }

    }
}