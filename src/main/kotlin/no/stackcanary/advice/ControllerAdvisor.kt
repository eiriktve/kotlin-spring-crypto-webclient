package no.stackcanary.advice

import no.stackcanary.resource.dto.ErrorDto
import no.stackcanary.webclient.exception.CoinGeckoRestException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdvisor : ResponseEntityExceptionHandler() {

    val log: Logger = LoggerFactory.getLogger(ControllerAdvisor::class.java)

    /**
     * Fallback handler for top level exceptions
     */
    @ExceptionHandler(Throwable::class)
    fun fallBackHandler(e: Throwable): ResponseEntity<Any> {
        log.error("Entered fallback handler with error message: ${e.localizedMessage}", e)
        val error = ErrorDto(
            HttpStatus.INTERNAL_SERVER_ERROR, e.localizedMessage,
            "An unexpected error occurred",
            LocalDateTime.now()
        )
        return ResponseEntity(error, HttpHeaders(), error.status)
    }

    @ExceptionHandler(CoinGeckoRestException::class)
    fun geckoExceptionHandler(e: CoinGeckoRestException): ResponseEntity<Any> {
        log.error("Advice caught GeckoException with error message: ${e.localizedMessage}", e)
        val error = ErrorDto(
            HttpStatus.valueOf(e.httpCode),
            e.message ?: "no message",
            e.ex.localizedMessage,
            LocalDateTime.now()
        )
        return ResponseEntity(error, HttpHeaders(), error.status)
    }
}