package no.stackcanary.kotlinspringbootrest.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties("webclient.coingecko")
class WebClientProps {
    lateinit var url: String
    lateinit var allCoinsResource: String
}