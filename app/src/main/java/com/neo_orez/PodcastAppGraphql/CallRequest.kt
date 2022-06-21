package com.neo_orez.PodcastAppGraphql

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.network.okHttpClient
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.type.*
import okhttp3.OkHttpClient

   class CallRequest {

        private var userKey = "96805445-8e2d-4aa4-8e3b-d64b9f8f0e75"
        private var userSecret = "K7FMi2EOkuz02YveOPyKnGZJAnwJCV0XX8BHTLge"
        private val BASEURL = "https://api.podchaser.com/graphql"
        private val launche = mutableListOf<DataQuery.Data1>()
        private val adapter0 = RecyclerAdapter(launche)

        suspend fun CallApollo(): String {
            val apolloClient = ApolloClient.Builder()
                .serverUrl(BASEURL)
                .build()
            ////////request Token
            val responseToken0 = apolloClient.mutation(
                TokenMutation(
                    AccessTokenRequest(OAuthGrantType.CLIENT_CREDENTIALS, userKey, userSecret)
                ))
                .execute()
            var Token0 = responseToken0.data!!.requestAccessToken!!.access_token
            Log.d("apollo0", Token0)
            return Token0
        }
        ///////fetch Data with access_token
        suspend fun ApolloData(token0:String): List<DataQuery.Data1> {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor(token0))
                .build()
            val apolloClient1 = ApolloClient.Builder()
                .serverUrl(BASEURL)
                .okHttpClient(okHttpClient)
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

            val  ResponseData = responseData.data!!.podcasts!!.data
           // Log.d("apollo3", ResponseData.toString())
            launche.addAll(ResponseData)
            adapter0.notifyDataSetChanged()
            return ResponseData
        }
   }