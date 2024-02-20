package no.stackcanary.resource.dto

import kotlinx.serialization.Serializable
import no.stackcanary.util.LocalDateTimeSerializer
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Created and returned by our ControllerAdvice
 */
@Serializable
data class ErrorDto(
    val status: HttpStatus,
    val message: String,
    val error: String,
    @Serializable(with = LocalDateTimeSerializer::class) val timeStamp: LocalDateTime
)

@Serializable
data class GenericDto(
    val httpStatus: Int,
    val message: String
)