package no.stackcanary.service

import no.stackcanary.repository.model.Currency
import no.stackcanary.webclient.response.CurrencyResponse
import org.springframework.stereotype.Service

/**
 * Transforms remote API responses to application models
 */
@Service
class TransformerService {
    fun fromCurrencyResponseToCurrency(from: List<CurrencyResponse>): List<Currency> =
        from.map { Currency(0L, it.name, it.symbol, null) }
}

