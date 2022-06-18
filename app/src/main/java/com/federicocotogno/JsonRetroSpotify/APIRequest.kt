package com.federicocotogno.JsonRetroSpotify

import com.federicocotogno.JsonRetroSpotify.api.Podcasts
import retrofit2.http.GET

interface APIRequest {

    @GET("v1/api.json?rss_url=https://tehranpodcast.ir/feed/")
    suspend fun getNews() : Podcasts

}