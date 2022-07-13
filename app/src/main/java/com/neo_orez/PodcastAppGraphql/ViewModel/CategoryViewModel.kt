package com.neo_orez.PodcastAppGraphql.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neo_orez.PodcastAppGraphql.DataCategoriesQuery
import com.neo_orez.PodcastAppGraphql.DataQueryHotQuery

class CategoryViewModel : ViewModel() {

    var dataCatLive = MutableLiveData<ArrayList<DataCategoriesQuery.Data1>>()
    val dataCat = ArrayList<DataCategoriesQuery.Data1> ()

    fun CatData(daTa:ArrayList<DataCategoriesQuery.Data1>) {
        dataCat.addAll(daTa)
        dataCatLive.value = dataCat

        Log.d("logViewMode20", dataCatLive.toString())
        Log.d("logViewMode21", dataCat.toString())
    }

}