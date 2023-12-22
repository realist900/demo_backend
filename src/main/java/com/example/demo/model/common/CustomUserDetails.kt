package com.example.demo.model.common

import com.example.demo.model.table.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(val user: User) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList() // Пока что нет ролей
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.login
    }

    override fun isAccountNonExpired(): Boolean {
        return true // Учётная запись не истекает
    }

    override fun isAccountNonLocked(): Boolean {
        return true // Учётная запись не заблокирована
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true // Учётные данные не истекли
    }

    override fun isEnabled(): Boolean {
        return true // Учётная запись активна
    }
}