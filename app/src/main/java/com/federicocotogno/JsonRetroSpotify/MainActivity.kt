package com.federicocotogno.JsonRetroSpotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.network.okHttpClient
import com.federicocotogno.JsonRetroSpotify.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import om.federicocotogno.JsonRetroSpotify.DataQuery
import om.federicocotogno.JsonRetroSpotify.TokenMutation
import om.federicocotogno.JsonRetroSpotify.type.*


class MainActivity : AppCompatActivity() {

    lateinit var Token0: String
    private var userKey = "96805445-8e2d-4aa4-8e3b-d64b9f8f0e75"
    private var userSecret = "K7FMi2EOkuz02YveOPyKnGZJAnwJCV0XX8BHTLge"
    private val BASEURL = "https://api.podchaser.com/graphql"
    val launche = mutableListOf<DataQuery.Data1>()
    private val adapter0 = RecyclerAdapter(launche)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // makeAPIRequest()
        GlobalScope.launch(Dispatchers.IO) { CallApollo() }
        rv_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    suspend fun CallApollo() {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://api.podchaser.com/graphql")
            .build()
        ////////request Token
        val responseToken0 = apolloClient.mutation(
            TokenMutation(
                AccessTokenRequest(OAuthGrantType.CLIENT_CREDENTIALS, userKey, userSecret)))
            .execute()
        Token0 = responseToken0.data!!.requestAccessToken!!.access_token
        Log.d("apollo0", Token0)
        ApolloData(Token0)

        ////////fetchJson with access_token

    }
    suspend fun ApolloData(token0:String) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor0(token0))
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
                ),
                first = Optional.presentIfNotNull(10)
            )
        ).execute()

        withContext(Dispatchers.Main){
            val  ResponseData = responseData.data!!.podcasts!!.data
            Log.d("apollo3", ResponseData.toString())

            if (ResponseData != null) {
                launche.addAll(ResponseData)
                adapter0.notifyDataSetChanged()
            }

            rv_recyclerView.adapter = RecyclerAdapter(ResponseData)
        }
    }
}
 class AuthorizationInterceptor0(private val context: String) : Interceptor {
     override fun intercept(chain: Interceptor.Chain): Response {
         val request = chain.request().newBuilder()
             .addHeader("Authorization", context)
             .build()
         return chain.proceed(request)
     }
 }


//class AuthorizationInterceptor(val token: String) : HttpInterceptor {
//    override suspend fun intercept(request: HttpRequest, chain: HttpInterceptorChain): HttpResponse {
//        val Auth = chain.proceed(request.newBuilder().addHeader("Authorization", "Bearer $token").build())
//        return Auth
//    }
//}
