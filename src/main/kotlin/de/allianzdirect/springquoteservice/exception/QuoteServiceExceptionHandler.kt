package de.allianzdirect.springquoteservice.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class QuoteServiceExceptionHandler {
    @ExceptionHandler(QuoteScoreTooLow::class)
    fun handleQuoteScoreTooLow(exception: Exception): ResponseEntity<Unit> =
        ResponseEntity.status(470).build()

    @ExceptionHandler(ApplicationPreviouslyRejected::class)
    fun handleApplicationPreviouslyRejected(exception: Exception): ResponseEntity<Unit> =
        ResponseEntity.status(499).build()
}