package com.neo_orez.PodcastAppGraphql.Apollo

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.neo_orez.PodcastAppGraphql.DataQuery
import com.neo_orez.PodcastAppGraphql.TokenMutation
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.type.*
import okhttp3.OkHttpClient

class CallRequest {

    private var userKey = "96805445-8e2d-4aa4-8e3b-d64b9f8f0e75"
    private var userSecret = "K7FMi2EOkuz02YveOPyKnGZJAnwJCV0XX8BHTLge"
    private val baseURL = "https://api.podchaser.com/graphql"
    private val launch = mutableListOf<DataQuery.Data1>()


    suspend fun apolloToken(): String {
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .build()
        ////////request Token
        val responseToken0 = apolloClient.mutation(
            TokenMutation(
                AccessTokenRequest(OAuthGrantType.CLIENT_CREDENTIALS, userKey, userSecret)
            )
        )
            .execute()
        val Token0 = responseToken0.data!!.requestAccessToken!!.access_token
        Log.d("apollo0", Token0)
        return Token0
    }

    ///////fetch Data with access_token
    suspend fun apolloData(token0:String): List<DataQuery.Data1> {
        //20 Megabyte of memory cache
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo.db")

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient1 = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()

        val responseData = apolloClient1.query(
            DataQuery(
                searchTerm = Optional.presentIfNotNull("فارسی"),
                sort = Optional.presentIfNotNull(
                    PodcastSort(
                        PodcastSortType.FOLLOWER_COUNT,
                        direction = Optional.presentIfNotNull(SortDirection.DESCENDING)
                    )
                ), first = Optional.presentIfNotNull(10)
            )
        ).execute()

        val  dataPods = responseData.data!!.podcasts!!.data
        // Log.d("apollo3", ResponseData.toString())
        launch.addAll(dataPods)
        RecyclerAdapter(launch).notifyDataSetChanged()
        return dataPods
    }
}