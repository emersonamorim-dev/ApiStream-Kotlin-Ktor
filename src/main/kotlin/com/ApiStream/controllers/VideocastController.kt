package com.ApiStream.controllers

import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import com.ApiStream.model.Videocast
import com.ApiStream.dto.VideocastDto
import com.ApiStream.dto.CreateVideocastDto
import com.ApiStream.kafka.producer.VideocastProducer
import com.ApiStream.services.VideocastService

class VideocastController(private val service: VideocastService, private val producer: VideocastProducer) {
    suspend fun getAll(call: ApplicationCall) {
        val videocasts = service.getAllVideocasts().map { VideocastDto(it.id, it.title, it.url) }
        call.respond(videocasts)
    }

    suspend fun create(call: ApplicationCall) {
        val createDto = call.receive<CreateVideocastDto>()
        val videocast = service.createVideocast(Videocast(0, createDto.title, createDto.url))
        producer.send(videocast) // enviar o videocast para o Kafka
        call.respond(HttpStatusCode.Created, VideocastDto(videocast.id, videocast.title, videocast.url))
    }

    suspend fun update(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            val updateDto = call.receive<CreateVideocastDto>()
            val updatedVideocast = service.updateVideocast(Videocast(id, updateDto.title, updateDto.url))
            producer.send(updatedVideocast) // enviar o videocast atualizado para o Kafka
            call.respond(HttpStatusCode.OK, VideocastDto(updatedVideocast.id, updatedVideocast.title, updatedVideocast.url))
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            service.deleteVideocast(id)
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

