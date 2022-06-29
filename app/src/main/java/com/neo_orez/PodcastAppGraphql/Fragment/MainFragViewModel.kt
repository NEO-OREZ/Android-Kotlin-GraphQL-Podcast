package com.neo_orez.PodcastAppGraphql.Fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neo_orez.PodcastAppGraphql.DataQuery

class MainFragViewModel : ViewModel() {
    var _dataListModel = arrayListOf<DataQuery.Data1>()
    var dataListModel = MutableLiveData<ArrayList<DataQuery.Data1>>()

}