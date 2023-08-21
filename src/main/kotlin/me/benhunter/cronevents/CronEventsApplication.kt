package me.benhunter.cronevents

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.SpringApplicationEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@SpringBootApplication
class CronEventsApplication

fun main(args: Array<String>) {
    runApplication<CronEventsApplication>(*args)
}

@Component
class EventLogger {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun listen(event: SpringApplicationEvent) {
        logger.info("SpringApplicationEvent Received - $event")
    }

    @EventListener
    fun listen(event: PlumbusEvent) {
        logger.info("Event Received - ID: ${event.id} - $event")
    }
}

@Configuration
@EnableScheduling
class EventPublisher(private val applicationEventPublisher: ApplicationEventPublisher) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private var count = 0L

    @Scheduled(fixedDelay = 1000)
    fun flamboxCongrepulator() {
        val plumbusEvent = PlumbusEvent(this, count)
        logger.info("Publishing event $plumbusEvent")
        applicationEventPublisher.publishEvent(plumbusEvent)
        count += 1
    }
}

class PlumbusEvent(source: Any, val id: Long) : ApplicationEvent(source) {
}
