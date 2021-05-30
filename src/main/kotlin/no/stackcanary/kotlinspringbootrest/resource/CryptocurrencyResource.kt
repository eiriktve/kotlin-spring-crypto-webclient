package no.stackcanary.kotlinspringbootrest.resource

import no.stackcanary.kotlinspringbootrest.repository.model.Currency
import no.stackcanary.kotlinspringbootrest.service.CurrencyService
import no.stackcanary.kotlinspringbootrest.webclient.response.CurrencyResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/api/crypto"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class CryptocurrencyResource(private val currencyService: CurrencyService) {


    @GetMapping("/currency/{abbrevName}")
    fun getCurrencyByAbbreviatedName(@PathVariable abbrevName: String): ResponseEntity<Currency> {
        val currency: Currency = currencyService.getCurrencyByAbbreviatedName(abbrevName)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(currency)
    }

    @GetMapping("/currency")
    suspend fun getAllCurrencies(): ResponseEntity<List<Currency>> {
        val currencies = currencyService.getAllCurrencies()
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(currencies);
    }
}