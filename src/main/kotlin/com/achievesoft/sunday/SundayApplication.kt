package com.achievesoft.sunday

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.achievesoft.sunday.repositories"])
class SundayApplication

fun main(args: Array<String>) {
	runApplication<SundayApplication>(*args)
}
