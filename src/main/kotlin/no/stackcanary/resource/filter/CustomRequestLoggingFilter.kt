package no.stackcanary.resource.filter

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.filter.CommonsRequestLoggingFilter

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