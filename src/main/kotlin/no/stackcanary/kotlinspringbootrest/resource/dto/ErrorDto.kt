package no.stackcanary.kotlinspringbootrest.resource.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorDto(
    val status: HttpStatus,
    val message: String,
    val error: String,
    val timeStamp: LocalDateTime
)
