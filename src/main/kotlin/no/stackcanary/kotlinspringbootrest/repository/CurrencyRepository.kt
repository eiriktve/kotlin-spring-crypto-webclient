package no.stackcanary.kotlinspringbootrest.repository

import no.stackcanary.kotlinspringbootrest.repository.model.Currency
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface CurrencyRepository: CrudRepository<Currency, Long> {

    @Query("select * from currency")
    fun getAllCoins(): List<Currency>

    @Query("select * from currency where abbrev_name = :abbrevName")
    fun getCurrencyByAbbreviatedName(@Param("abbrevName") abbrevName: String): List<Currency>
}
