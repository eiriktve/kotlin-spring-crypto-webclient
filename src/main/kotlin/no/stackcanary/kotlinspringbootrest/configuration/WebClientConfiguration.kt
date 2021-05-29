package no.stackcanary.kotlinspringbootrest.configuration

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit


/**
 * Configured for CoinGecko resources
 */
@Configuration
class WebClientConfiguration(private val webClientProps: WebClientProps) {

    /**
     * Customized HttpClient to be used in our WebClient configuration
     */
    @Bean
    protected fun httpClient(): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected { conn: Connection ->
                conn.addHandlerLast(ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
            }
    }

    /**
     * Upping the memory size because we are calling endpoints which return a lot of data
     */
    @Bean
    fun webclient(): WebClient {
        return WebClient.builder()
            .baseUrl(webClientProps.url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient()))
            .exchangeStrategies(
                ExchangeStrategies
                    .builder()
                    .codecs({ it.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)} )
                    .build())
            .build()
    }

}