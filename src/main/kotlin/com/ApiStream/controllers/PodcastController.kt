package com.ApiStream.controllers

import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import com.ApiStream.model.Podcast
import com.ApiStream.dto.PodcastDto
import com.ApiStream.dto.CreatePodcastDto
import com.ApiStream.services.PodcastService

class PodcastController(private val service: PodcastService) {
    suspend fun getAll(call: ApplicationCall) {
        val podcasts = service.getAllPodcasts().map { PodcastDto(it.id, it.title, it.url) }
        call.respond(podcasts)
    }

    suspend fun create(call: ApplicationCall) {
        val createDto = call.receive<CreatePodcastDto>()
        val podcast = service.createPodcast(Podcast(0, createDto.title, createDto.url))
        call.respond(HttpStatusCode.Created, PodcastDto(podcast.id, podcast.title, podcast.url))
    }

    suspend fun update(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            val updateDto = call.receive<CreatePodcastDto>()
            val updatedPodcast = service.updatePodcast(Podcast(id, updateDto.title, updateDto.url))
            call.respond(HttpStatusCode.OK, PodcastDto(updatedPodcast.id, updatedPodcast.title, updatedPodcast.url))
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            service.deletePodcast(id)
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}



