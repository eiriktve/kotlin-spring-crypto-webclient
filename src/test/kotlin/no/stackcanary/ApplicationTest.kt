package no.stackcanary

import io.mockk.every
import io.mockk.mockk
import no.stackcanary.repository.CurrencyRepository
import no.stackcanary.repository.model.Currency
import no.stackcanary.webclient.CoinGeckoClient
import no.stackcanary.webclient.response.CurrencyResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.net.URI


@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = [ApplicationTest.ClientTestConfig::class])
@ActiveProfiles("test")
class ApplicationTest {

    @LocalServerPort
    var serverPort: Int = 0

    val testRestTemplate = TestRestTemplate()

    @Autowired
    lateinit var repository: CurrencyRepository

    @Test
    fun `database populator test`() {
        val result = testRestTemplate.exchange(
            URI(applicationUrl() + "/api/db/populate"),
            HttpMethod.POST,
            HttpEntity(""),
            String::class.java)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(3, repository.getAllCoins().size)

        val btc: List<Currency> = repository.getCurrencyByAbbreviatedName("btc")
        Assertions.assertNotNull(btc)
        Assertions.assertTrue(btc.isNotEmpty())
    }

    private fun applicationUrl() = "http://localhost:$serverPort"

    @TestConfiguration
    internal class ClientTestConfig() {
        @Bean
        fun coinGeckoClient(): CoinGeckoClient {
            val clientMock = mockk<CoinGeckoClient>()
            every { clientMock.getCurrencies() } returns listOf(
                CurrencyResponse("1", "btc", "bitcoin"),
                CurrencyResponse("2", "ftm", "fantom"),
                CurrencyResponse("3", "eth", "etherium")
            )
            return clientMock
        }
    }
}