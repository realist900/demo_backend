package com.example.demo.handler

import com.example.demo.common.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceNotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}