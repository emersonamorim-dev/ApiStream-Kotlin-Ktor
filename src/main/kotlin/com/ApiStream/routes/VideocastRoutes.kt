package com.ApiStream.routes

import com.ApiStream.repositories.VideocastRepository
import com.ApiStream.repositories.InMemoryVideocastRepository
import com.ApiStream.services.DefaultVideocastService
import com.ApiStream.services.VideocastService
import com.ApiStream.model.Videocast



val videocastRepository: VideocastRepository = InMemoryVideocastRepository()
val videocastService: VideocastService = DefaultVideocastService(videocastRepository)

fun Route.registerVideocastRoutes() {
    route("/videocasts") {
        get {
            call.respond(videocastService.getAllVideocasts())
        }
        post {
            val videocast = call.receive<Videocast>()
            val createdVideocast = videocastService.createVideocast(videocast)
            GlobalScope.launch { eventManager.notifyVideocastCreated(createdVideocast) }
            call.respond(HttpStatusCode.Created, createdVideocast)
        }
    }
}
