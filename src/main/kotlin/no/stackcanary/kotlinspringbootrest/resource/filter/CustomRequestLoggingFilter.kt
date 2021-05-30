package no.stackcanary.kotlinspringbootrest.resource.filter

import org.springframework.web.filter.CommonsRequestLoggingFilter
import javax.servlet.http.HttpServletRequest

/**
 * For request logging in our resources
 */
class CustomRequestLoggingFilter: CommonsRequestLoggingFilter() {
    init {
        super.setIncludeQueryString(true)
        super.setIncludePayload(true)
        super.setMaxPayloadLength(10000)
    }

    override fun afterRequest(request: HttpServletRequest, message: String) {
        /* Leaving this empty to avoid duplicate logging */
    }
}