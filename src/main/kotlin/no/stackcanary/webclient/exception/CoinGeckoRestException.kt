package no.stackcanary.webclient.exception

import org.springframework.web.client.RestClientException

class CoinGeckoRestException(val httpCode: Int, private val msg: String, val ex: Throwable) : RestClientException(msg, ex)
