package com.ApiStream.services

import com.ApiStream.repositories.PodcastRepository
import com.ApiStream.model.Podcast

interface PodcastService {
    fun getAllPodcasts(): List<Podcast>
    fun createPodcast(podcast: Podcast): Podcast
    fun updatePodcast(podcast: Podcast): Podcast
    fun deletePodcast(id: Int): Unit
}

class DefaultPodcastService(private val repository: PodcastRepository) : PodcastService {
    override fun getAllPodcasts(): List<Podcast> {
        return repository.getAll()
    }

    override fun createPodcast(podcast: Podcast): Podcast {
        return repository.add(podcast)
    }

    override fun updatePodcast(podcast: Podcast): Podcast {
        val existingPodcast = repository.findById(podcast.id)
        if (existingPodcast != null) {
            existingPodcast.title = podcast.title
            existingPodcast.url = podcast.url
            repository.update(existingPodcast)
            return existingPodcast
        } else {
            throw IllegalArgumentException("Podcast not found with id: ${podcast.id}")
        }
    }
    

    override fun deletePodcast(id: Int) {
        val existingPodcast = repository.findById(id)
        if (existingPodcast != null) {
            repository.delete(id)
        } else {
            throw IllegalArgumentException("Podcast not found with id: $id")
        }
    }
}


