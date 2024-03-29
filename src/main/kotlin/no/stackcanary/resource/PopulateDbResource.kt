package no.stackcanary.resource

import no.stackcanary.resource.dto.GenericDto
import no.stackcanary.service.PopulateDbService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    companion object {
        private val log: Logger = LoggerFactory.getLogger(PopulateDbResource::class.java)
        var populated = false
    }


    @PostMapping("/populate")
    fun populate(): ResponseEntity<GenericDto> {
        return if (populated) {
            ResponseEntity.badRequest()
                .body(GenericDto(HttpStatus.BAD_REQUEST.value(), "Database has already been populated"))
        } else {
            log.info("Populating database with data from CoinGecko")
            service.populateDbWithDataFromCoinGecko()
            populated = true
            ResponseEntity.ok(GenericDto(HttpStatus.OK.value(), "Database population done"))
        }

    }
}