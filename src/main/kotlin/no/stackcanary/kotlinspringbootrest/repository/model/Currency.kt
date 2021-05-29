package no.stackcanary.kotlinspringbootrest.repository.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("CURRENCY")
data class Currency(
    @Id val id: Long,
    val name: String,
    val abbreviatedName: String,
    val createdDate: LocalDate
)
