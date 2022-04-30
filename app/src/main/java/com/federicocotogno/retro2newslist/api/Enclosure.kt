package com.federicocotogno.retro2newslist.api

data class Enclosure(
    val duration: Int,
    val length: Int,
    val link: String,
    val rating: Rating,
    val type: String
)