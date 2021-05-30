package no.stackcanary.kotlinspringbootrest.resource

import no.stackcanary.kotlinspringbootrest.resource.dto.GenericDto
import no.stackcanary.kotlinspringbootrest.service.PopulateDbService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Since we are using a non-persistent db like H2, and our coin data comes from an external
 * service, we've made a resource that we can use to populate the db when our service is up and
 * running.
 *
 * Note: This could arguably have been done more correctly with a CommandLineRunner, but we want to
 * test stuff out.
 */
@RequestMapping(path = ["/api/db"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class PopulateDbResource(private val service: PopulateDbService) {

    @PostMapping("/populate")
    fun populate(): ResponseEntity<GenericDto> {
        service.populateDbWithDataFromCoinGecko()
        return ResponseEntity.ok(GenericDto(HttpStatus.OK.value(), "Database has been populated"))
    }
}