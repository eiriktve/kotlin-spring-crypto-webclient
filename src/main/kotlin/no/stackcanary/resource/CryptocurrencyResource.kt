package no.stackcanary.resource

import no.stackcanary.repository.model.Currency
import no.stackcanary.service.CurrencyService
import no.stackcanary.webclient.response.EmptyResultResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/api/crypto"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class CryptocurrencyResource(private val currencyService: CurrencyService) {

    val log: Logger = LoggerFactory.getLogger(CryptocurrencyResource::class.java)

    @GetMapping("/currency/{abbrevName}")
    fun getCurrencyByAbbreviatedName(@PathVariable abbrevName: String): ResponseEntity<Any> {
        log.info("Retrieving currency from database with abbrevName $abbrevName")
        val currencies: List<Currency> = currencyService.getCurrencyByAbbreviatedName(abbrevName)

        if (currencies.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(EmptyResultResponse("$abbrevName does not exist"))
        }

        return ResponseEntity.ok(currencies)
    }

    @GetMapping("/currency")
    suspend fun getAllCurrencies(): ResponseEntity<List<Currency>> {
        log.info("Retrieving all currencies from database")
        val currencies = currencyService.getAllCurrencies()
        return ResponseEntity.ok(currencies)
    }
}