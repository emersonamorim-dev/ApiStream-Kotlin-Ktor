package com.ApiStream.dto

data class PodcastDto(
    val id: Int,
    val title: String,
    val url: String
)

data class CreatePodcastDto(
    val title: String,
    val url: String
)
