package no.stackcanary.kotlinspringbootrest.exception

import org.springframework.web.client.RestClientException
import java.lang.RuntimeException

class CoinGeckoRestException(val httpCode: Int, val msg: String, val ex: Throwable) :
    RestClientException(msg, ex)
