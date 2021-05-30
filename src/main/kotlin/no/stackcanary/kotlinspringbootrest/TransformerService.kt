package no.stackcanary.kotlinspringbootrest

import no.stackcanary.kotlinspringbootrest.repository.model.Currency
import no.stackcanary.kotlinspringbootrest.webclient.response.CurrencyResponse
import org.springframework.stereotype.Service

/**
 * Transforms remote API responses to application models
 */

@Service
class TransformerService {

    fun fromCurrencyResponseToCurrency(from: List<CurrencyResponse>): List<Currency> =
        from.map { Currency(0L, it.name, it.symbol, null) }.toList()
}