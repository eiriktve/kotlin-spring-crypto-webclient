package no.stackcanary.kotlinspringbootrest.service

import no.stackcanary.kotlinspringbootrest.repository.CurrencyRepository
import no.stackcanary.kotlinspringbootrest.webclient.CoinGeckoClient
import no.stackcanary.kotlinspringbootrest.webclient.response.CurrencyResponse
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