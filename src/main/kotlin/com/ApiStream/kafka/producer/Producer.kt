package com.ApiStream.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun createProducer(): KafkaProducer<String, String> {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["key.serializer"] = StringSerializer::class.java.canonicalName
    props["value.serializer"] = StringSerializer::class.java.canonicalName
    return KafkaProducer<String, String>(props)
}

fun sendEvent(producer: KafkaProducer<String, String>, topic: String, key: String, value: String) {
    val record = ProducerRecord<String, String>(topic, key, value)
    producer.send(record)
    producer.flush()
}
