package no.stackcanary.service

import no.stackcanary.repository.CurrencyRepository
import no.stackcanary.repository.model.Currency
import org.springframework.stereotype.Service

@Service
class CurrencyService(private val repository: CurrencyRepository) {

    fun getAllCurrencies(): List<Currency> = repository.getAllCoins()

    fun getCurrencyByAbbreviatedName(abbrevName: String): List<Currency> =
        repository.getCurrencyByAbbreviatedName(abbrevName)

    fun saveCoin(currency: Currency) = repository.save(currency)
}


