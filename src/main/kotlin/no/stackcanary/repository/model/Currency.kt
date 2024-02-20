package no.stackcanary.repository.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("CURRENCY")
data class Currency(
    @Id val id: Long,
    val name: String,
    @Column("ABBREV_NAME")
    val abbreviatedName: String,
    @Column("CREATED")
    val createdDate: LocalDate?
)

