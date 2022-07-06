package com.neo_orez.PodcastAppGraphql.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neo_orez.PodcastAppGraphql.DataQueryHotQuery


class FirstFragViewModel : ViewModel() {

    var dataHotLive = MutableLiveData<ArrayList<DataQueryHotQuery.Data1>>()
    val dataHot = ArrayList<DataQueryHotQuery.Data1> ()

    fun HotData(daTa:ArrayList<DataQueryHotQuery.Data1>){
        dataHot.addAll(daTa)
        dataHotLive.value = dataHot

        Log.d("logViewModel0", dataHotLive.toString())
        Log.d("logViewModel1", dataHot.toString())
    }
}