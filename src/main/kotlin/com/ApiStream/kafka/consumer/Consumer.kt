package com.ApiStream.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

fun createConsumer(): KafkaConsumer<String, String> {
    val props = Properties()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    props[ConsumerConfig.GROUP_ID_CONFIG] = "my-group"
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.canonicalName
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.canonicalName
    return KafkaConsumer<String, String>(props)
}

fun consumeEvents(consumer: KafkaConsumer<String, String>, topic: String) {
    consumer.subscribe(listOf(topic))

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            println("Received event: ${record.value()} (key: ${record.key()})")
        }
    }
}
