package no.stackcanary.resource.filter

import jakarta.servlet.ServletContext
import jakarta.servlet.ServletRegistration
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

/**
 * Inject our CustomRequestLoggingFilter using java based web initializer
 *
 * @see CustomRequestLoggingFilter
 */
class CustomWebAppInitializer : WebApplicationInitializer {

    override fun onStartup(container: ServletContext) {
        val context = AnnotationConfigWebApplicationContext()
        context.setConfigLocation("no.stackcanary.resource.filter")
        container.addListener(ContextLoaderListener(context))

        val dispatcher: ServletRegistration.Dynamic =
            container.addServlet("dispatcher", DispatcherServlet(context))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")

        container.addFilter("customRequestLoggingFilter", CustomRequestLoggingFilter::class.java)
            .addMappingForServletNames(null, false, "dispatcher")

    }
}