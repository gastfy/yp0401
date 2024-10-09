package com.example.demo.errors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFound::class)
    fun handleBookNotFoundException(ex: BookNotFound, request: WebRequest): ResponseEntity<*> {
        val errorDetails = ErrorDetails(Date(), ex.message.orEmpty(), request.getDescription(false))
        return ResponseEntity<Any>(errorDetails.toJSON(), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(GenreNotFound::class)
    fun handleGenreNotFoundException(ex: GenreNotFound, request: WebRequest): ResponseEntity<*> {
        val errorDetails = ErrorDetails(Date(), ex.message.orEmpty(), request.getDescription(false))
        return ResponseEntity(errorDetails.toJSON(), HttpStatus.NOT_FOUND)
    }

}