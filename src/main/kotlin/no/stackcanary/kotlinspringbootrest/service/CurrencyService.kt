package no.stackcanary.kotlinspringbootrest.service

import no.stackcanary.kotlinspringbootrest.repository.CurrencyRepository
import no.stackcanary.kotlinspringbootrest.repository.model.Currency
import no.stackcanary.kotlinspringbootrest.webclient.CoinGeckoClient
import no.stackcanary.kotlinspringbootrest.webclient.response.CurrencyResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CurrencyService(
    private val repository: CurrencyRepository,
    private val coinGeckoClient: CoinGeckoClient
) {

    fun getCoinsFromGecko(): List<CurrencyResponse> = coinGeckoClient.getCurrencies()

    fun getCoins(): List<Currency> = repository.getAllCoins()

    fun getCoinById(id: Long): Currency? = repository.findById(id).get()

    fun saveCoin(currency: Currency) = repository.save(currency)
}