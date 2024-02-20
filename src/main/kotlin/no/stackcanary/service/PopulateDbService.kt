package no.stackcanary.service

import no.stackcanary.repository.CurrencyRepository
import no.stackcanary.webclient.CoinGeckoClient
import no.stackcanary.webclient.response.CurrencyResponse
import org.springframework.stereotype.Service

@Service
class PopulateDbService(
    private val geckoClient: CoinGeckoClient,
    private val currencyRepository: CurrencyRepository,
    private val transformer: TransformerService
) {

    fun populateDbWithDataFromCoinGecko() {
        val currencies: List<CurrencyResponse> = geckoClient.getCurrencies()
        currencyRepository.saveAll(transformer.fromCurrencyResponseToCurrency(currencies))
    }
}

