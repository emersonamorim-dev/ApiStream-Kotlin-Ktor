package com.ApiStream.repositories

import com.ApiStream.model.Podcast


interface PodcastRepository {
    fun getAll(): List<Podcast>
    fun findById(id: Int): Podcast?
    fun add(podcast: Podcast): Podcast
    fun update(podcast: Podcast): Podcast
    fun delete(id: Int): Unit
}

class InMemoryPodcastRepository : PodcastRepository {
    private val podcasts = mutableListOf<Podcast>()
    private var nextId = 1

    override fun getAll(): List<Podcast> {
        return podcasts
    }

    override fun findById(id: Int): Podcast? {
        return podcasts.find { it.id == id }
    }

    override fun add(podcast: Podcast): Podcast {
        podcast.id = nextId++
        podcasts.add(podcast)
        return podcast
    }

    override fun update(podcast: Podcast): Podcast {
        val index = podcasts.indexOfFirst { it.id == podcast.id }
        if (index != -1) {
            podcasts[index] = podcast
            return podcast
        } else {
            throw IllegalArgumentException("Podcast não encontrado com id: ${podcast.id}")
        }
    }

    override fun delete(id: Int) {
        val index = podcasts.indexOfFirst { it.id == id }
        if (index != -1) {
            podcasts.removeAt(index)
        } else {
            throw IllegalArgumentException("Podcast não encontrado com with id: $id")
        }
    }
}
