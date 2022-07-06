package com.neo_orez.PodcastAppGraphql.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FirstFragViewModel::class.java)){
            return FirstFragViewModel() as T

        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}