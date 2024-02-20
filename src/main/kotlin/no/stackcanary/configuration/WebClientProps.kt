package no.stackcanary.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("webclient.coingecko")
class WebClientProps {
    lateinit var url: String
    lateinit var allCoinsResource: String
}