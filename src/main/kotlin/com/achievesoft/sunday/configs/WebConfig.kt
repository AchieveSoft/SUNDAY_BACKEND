package com.achievesoft.sunday.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebConfig {
    @Bean
    fun corsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedMethods("GET", "POST", "PUT")
                    .allowedOrigins("http://localhost:5173", "http://sunday.achieve-soft.com")
                    .allowCredentials(true)
            }
        }
    }
}