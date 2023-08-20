package me.benhunter.cronevents

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CronEventsApplication

fun main(args: Array<String>) {
	runApplication<CronEventsApplication>(*args)
}
