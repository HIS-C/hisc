package com.hisc.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfiguration : WebMvcConfigurer {
    companion object {
        val RESOURCE_LOCATIONS = arrayOf("classpath:/static/")
    }

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Bean
    fun webServerFactory(): ConfigurableServletWebServerFactory {
        val factory = TomcatServletWebServerFactory()
        factory.addErrorPages(ErrorPage(HttpStatus.NOT_FOUND, "/error"))
        factory.addErrorPages(ErrorPage(HttpStatus.BAD_REQUEST, "/error"))
        return factory
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter().also {
            it.objectMapper = objectMapper
        }
    }

    @Bean
    fun sessionHandlerInterceptor(): SessionHandlerInterceptor {
        return SessionHandlerInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(sessionHandlerInterceptor())
            .addPathPatterns("/**", "/static/**")
            .excludePathPatterns(
                "/health",
                "/hospital/**"
            )
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        // Replace MappingJackson2HttpMessageConverter
        converters.replaceAll {
            if (it is MappingJackson2HttpMessageConverter) {
                mappingJackson2HttpMessageConverter()
            } else {
                it
            }
        }
    }
}
