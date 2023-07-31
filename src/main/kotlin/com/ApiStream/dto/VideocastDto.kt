package com.ApiStream.dto

data class VideocastDto(
    val id: Int,
    val title: String,
    val url: String
)

data class CreateVideocastDto(
    val title: String,
    val url: String
)