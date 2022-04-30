package com.federicocotogno.retro2newslist

import com.federicocotogno.retro2newslist.api.JsonPodcast
import com.federicocotogno.retro2newslist.api.NewsJSON
import retrofit2.http.GET

interface APIRequest {

    @GET("v1/api.json?rss_url=https://tehranpodcast.ir/feed/")
    suspend fun getNews() : JsonPodcast

}