package no.stackcanary.webclient.response

import kotlinx.serialization.Serializable
import no.stackcanary.util.LocalDateTimeSerializer
import java.time.LocalDateTime

// This file contains value classes / DTOs for different responses from CoinGecko

/**
 * Data class with an empty constructor
 */
@Serializable
data class CurrencyResponse(
    var id: String = "",
    var symbol: String = "",
    var name: String = ""
)

@Serializable
data class EmptyResultResponse(
    val message: String,
    @Serializable(with = LocalDateTimeSerializer::class) val timeStamp: LocalDateTime = LocalDateTime.now()
)

