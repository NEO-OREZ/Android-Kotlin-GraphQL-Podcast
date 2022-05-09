package com.federicocotogno.JsonRetroSpotify.api

data class JsonPodcast(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)