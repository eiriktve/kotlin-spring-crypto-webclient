package no.stackcanary.kotlinspringbootrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@SpringBootApplication
@ConfigurationPropertiesScan("no.stackcanary.kotlinspringbootrest.configuration")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
