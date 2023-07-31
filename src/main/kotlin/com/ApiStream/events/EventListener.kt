package com.ApiStream.events

import com.ApiStream.model.Podcast
import com.ApiStream.model.Videocast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface EventListener {
    suspend fun onPodcastCreated(podcast: Podcast)
    suspend fun onVideocastCreated(videocast: Videocast)
}

object EventManager {
    private val listeners = mutableListOf<EventListener>()

    fun addListener(listener: EventListener) {
        listeners.add(listener)
    }

    fun notifyPodcastCreated(podcast: Podcast) {
        GlobalScope.launch {
            listeners.forEach { it.onPodcastCreated(podcast) }
        }
    }

    fun notifyVideocastCreated(videocast: Videocast) {
        GlobalScope.launch {
            listeners.forEach { it.onVideocastCreated(videocast) }
        }
    }
}
