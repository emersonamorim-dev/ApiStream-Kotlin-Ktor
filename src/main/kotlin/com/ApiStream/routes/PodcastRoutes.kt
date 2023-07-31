package com.ApiStream.routes

import com.ApiStream.model.Podcast
import com.ApiStream.events.EventManager
import com.ApiStream.repositories.PodcastRepository
import com.ApiStream.repositories.InMemoryPodcastRepository
import com.ApiStream.services.PodcastService
import com.ApiStream.services.DefaultPodcastService
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.Route
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


val podcastRepository: PodcastRepository = InMemoryPodcastRepository()
val podcastService: PodcastService = DefaultPodcastService(podcastRepository)

fun Route.registerPodcastRoutes() {
    route("/podcasts") {
        get { controller.getAll(call) }
        post { controller.create(call) }
        put("/{id}") { controller.update(call) }
        delete("/{id}") { controller.delete(call) }
    }
}

