package no.stackcanary.kotlinspringbootrest.webclient.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

// This file contains value classes / DTOs for different responses from CoinGecko

/**
 * Data class with an empty constructor
 */
data class CurrencyResponse(
    var id: String = "",
    var symbol: String = "",
    var name: String = ""
)

data class EmptyResultResponse(
    val message: String,
    val timeStamp: LocalDateTime = LocalDateTime.now()
)

