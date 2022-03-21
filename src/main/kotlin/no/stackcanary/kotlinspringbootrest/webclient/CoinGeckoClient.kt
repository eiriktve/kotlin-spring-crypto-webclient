package no.stackcanary.kotlinspringbootrest.webclient

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import no.stackcanary.kotlinspringbootrest.configuration.WebClientProps
import no.stackcanary.kotlinspringbootrest.exception.CoinGeckoRestException
import no.stackcanary.kotlinspringbootrest.webclient.response.CurrencyResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.reactive.function.client.createExceptionAndAwait
import reactor.core.publisher.Mono

@Component
class CoinGeckoClient(private val client: WebClient, private val webClientProps: WebClientProps) {

    fun getCurrencies(): List<CurrencyResponse> {
        val jsonResponse: String? = client.get()
            .uri(webClientProps.allCoinsResource)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { it.createException() }
            .onStatus(HttpStatus::is5xxServerError) { it.createException() }
            .bodyToMono(String::class.java).block()

        val mapper = ObjectMapper()
        return mapper.readValue(jsonResponse, object : TypeReference<List<CurrencyResponse>>() {})
    }
}

