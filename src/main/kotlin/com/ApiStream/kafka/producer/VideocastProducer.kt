package com.ApiStream.kafka.producer


import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import com.ApiStream.model.Videocast
import com.google.gson.Gson
import org.apache.kafka.common.serialization.Serializer
import java.nio.charset.StandardCharsets
import java.util.*


class VideocastProducer {
    private val props = Properties().apply {
        put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VideocastSerializer::class.java)
    }
    private val producer = KafkaProducer<String, Videocast>(props)

    fun send(videocast: Videocast) {
        val record = ProducerRecord<String, Videocast>("videocast-topic", videocast)
        producer.send(record)
    }

    class VideocastSerializer : Serializer<Videocast> {
        private val gson = Gson()
    
        override fun serialize(topic: String?, data: Videocast?): ByteArray {
            return gson.toJson(data).toByteArray(StandardCharsets.UTF_8)
        }
    }
}

