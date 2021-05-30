package no.stackcanary.kotlinspringbootrest.resource.filter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

/**
 * Configuration for the spring built-in request logging
 */
@Configuration
class RequestLoggingFilterConfig {

    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true); // think twice before putting this to true
        filter.setBeforeMessagePrefix("Request: ");
        return filter;
    }

}