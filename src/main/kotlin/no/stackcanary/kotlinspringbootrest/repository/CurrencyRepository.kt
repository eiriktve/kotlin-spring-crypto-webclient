package no.stackcanary.kotlinspringbootrest.repository

import no.stackcanary.kotlinspringbootrest.repository.model.Currency
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository: CrudRepository<Currency, Long> {

    @Query("select * from coin")
    fun getAllCoins(): List<Currency>
}
