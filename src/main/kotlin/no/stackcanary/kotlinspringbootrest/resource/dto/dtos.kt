package no.stackcanary.kotlinspringbootrest.resource.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Created and returned by our ControllerAdvice
 */
data class ErrorDto(
    val status: HttpStatus,
    val message: String,
    val error: String,
    val timeStamp: LocalDateTime
)

data class GenericDto(
    val httpStatus: Int,
    val message: String
)
