package com.ApiStream.repositories

import com.ApiStream.model.Videocast

interface VideocastRepository {
    fun getAll(): List<Videocast>
    fun add(videocast: Videocast): Videocast
    fun update(videocast: Videocast): Videocast?
    fun delete(id: Int): Boolean
}

class InMemoryVideocastRepository : VideocastRepository {
    private val videocasts = mutableListOf<Videocast>()

    override fun getAll(): List<Videocast> {
        return videocasts
    }

    override fun add(videocast: Videocast): Videocast {
        videocasts.add(videocast)
        return videocast
    }

    override fun update(videocast: Videocast): Videocast? {
        val index = videocasts.indexOfFirst { it.id == videocast.id }
        return if (index != -1) {
            videocasts[index] = videocast
            videocast
        } else {
            null
        }
    }

    override fun delete(id: Int): Boolean {
        val index = videocasts.indexOfFirst { it.id == id }
        return if (index != -1) {
            videocasts.removeAt(index)
            true
        } else {
            false
        }
    }
}

