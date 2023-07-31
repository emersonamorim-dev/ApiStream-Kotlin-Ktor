package com.ApiStream.services

import com.ApiStream.model.Videocast
import com.ApiStream.repositories.VideocastRepository

interface VideocastService {
    fun getAllVideocasts(): List<Videocast>
    fun createVideocast(videocast: Videocast): Videocast
    fun updateVideocast(videocast: Videocast): Videocast
    fun deleteVideocast(id: Int): Boolean
}

class DefaultVideocastService(private val repository: VideocastRepository) : VideocastService {
    override fun getAllVideocasts(): List<Videocast> {
        return repository.getAll()
    }

    override fun createVideocast(videocast: Videocast): Videocast {
        return repository.add(videocast)
    }

    override fun updateVideocast(videocast: Videocast): Videocast {
        return repository.update(videocast)
            ?: throw IllegalArgumentException("Videocast not found with id: ${videocast.id}")
    }

    override fun deleteVideocast(id: Int): Boolean {
        return repository.delete(id)
    }
}

