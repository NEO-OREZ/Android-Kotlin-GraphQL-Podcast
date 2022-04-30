package com.federicocotogno.retro2newslist.api

data class JsonPodcast(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)