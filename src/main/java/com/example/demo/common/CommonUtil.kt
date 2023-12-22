package com.example.demo.common

import com.example.demo.model.common.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder

object CommonUtil {

    fun getUserId(): Int {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication.principal as CustomUserDetails).user.id
    }

}