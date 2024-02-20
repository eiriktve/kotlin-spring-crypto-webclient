package no.stackcanary.webclient

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import no.stackcanary.configuration.WebClientProps
import no.stackcanary.util.LocalDateTimeSerializer
import no.stackcanary.webclient.exception.CoinGeckoRestException
import no.stackcanary.webclient.response.CurrencyResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CoinGeckoClient(private val client: WebClient, private val webClientProps: WebClientProps) {

    companion object {
        private val json = Json {
            serializersModule = SerializersModule {
                contextual(LocalDateTimeSerializer)
            }
        }
    }

    fun getCurrencies(): List<CurrencyResponse> {
        val jsonResponse: String? = client.get()
            .uri(webClientProps.allCoinsResource)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        if (jsonResponse == null) {
            throw CoinGeckoRestException(500, "CoinGecko response was null", RuntimeException())
        }

        return json.decodeFromString(jsonResponse)
    }
}

