package com.neo_orez.PodcastAppGraphql.Apollo

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.neo_orez.PodcastAppGraphql.DataQuery
import com.neo_orez.PodcastAppGraphql.DataQueryHotQuery
import com.neo_orez.PodcastAppGraphql.TokenMutation
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapterHot
import com.neo_orez.PodcastAppGraphql.type.*
import okhttp3.OkHttpClient

class CallRequest {

    private var userKey = "96805445-8e2d-4aa4-8e3b-d64b9f8f0e75"
    private var userSecret = "K7FMi2EOkuz02YveOPyKnGZJAnwJCV0XX8BHTLge"
    private val baseURL = "https://api.podchaser.com/graphql"
    private val launch = ArrayList<DataQuery.Data1>()
    private val launchHot = ArrayList<DataQueryHotQuery.Data1>()


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
        Log.d("logCall_token", Token0)
        return Token0
    }

    ///////fetch Data with access_token
    suspend fun apolloDataFarsi(token0:String): ArrayList<DataQuery.Data1> {
        //20 Megabyte of memory cache
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_fa.db")

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

        val  dataPods  = responseData.data!!.podcasts!!.data as ArrayList
        // Log.d("apollo3", ResponseData.toString())
        launch.addAll(dataPods)
        RecyclerAdapter(launch).notifyDataSetChanged()
        return dataPods
    }
//////////////////////////////////////////////////////////////////////////////////////
    suspend fun apolloDataHot(token0:String): ArrayList<DataQueryHotQuery.Data1> {
        //20 Megabyte of memory cache
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_hot.db")

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient1 = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()

        val responseDataHot = apolloClient1.query(
            DataQueryHotQuery(filters = Optional.presentIfNotNull(
                PodcastFilters(rating = Optional.presentIfNotNull(
                        RatingFilter(Optional.presentIfNotNull(4.2), Optional.presentIfNotNull(5.0))
                    )
                )
            ))
        ).execute()

        val  dataPods  = responseDataHot.data?.podcasts?.data as ArrayList
         Log.d("logapollohot", dataPods.toString())
        launchHot.addAll(dataPods)
        RecyclerAdapterHot(launchHot).notifyDataSetChanged()
        return dataPods
    }
}