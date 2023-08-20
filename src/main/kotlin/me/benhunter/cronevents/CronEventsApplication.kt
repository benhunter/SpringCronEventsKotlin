package me.benhunter.cronevents

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.SpringApplicationEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@SpringBootApplication
class CronEventsApplication

fun main(args: Array<String>) {
    runApplication<CronEventsApplication>(*args)
}

@Component
class SpringApplicationEventLogger {
    private val logger = LoggerFactory.getLogger(javaClass)
    @EventListener
    fun listen(event: SpringApplicationEvent) {
        logger.info("SpringApplicationEvent Recieved - $event")
    }
}
