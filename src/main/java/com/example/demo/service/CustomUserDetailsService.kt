package com.example.demo.service

import com.example.demo.model.common.CustomUserDetails
import com.example.demo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user = userRepository.findByLogin(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return CustomUserDetails(user)
    }
}
