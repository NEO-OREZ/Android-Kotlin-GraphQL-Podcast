package com.neo_orez.PodcastAppGraphql.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.neo_orez.PodcastAppGraphql.Apollo.CallRequest
import com.neo_orez.PodcastAppGraphql.ViewModel.FirstFragViewModel
import com.neo_orez.PodcastAppGraphql.ViewModel.FirstViewModelFactory
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapterHot
import com.neo_orez.PodcastAppGraphql.databinding.FragmentFirsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirsFragment : Fragment() {
    private lateinit var bindingFirstFrag : FragmentFirsBinding
    private lateinit var viewModelHot : FirstFragViewModel
    private var factoryHot = FirstViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelHot = ViewModelProvider(this,factoryHot).get(FirstFragViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getToken = CallRequest().apolloToken()
            val getDataHot = CallRequest().apolloDataHot(getToken)
            Log.d("logfragfirst_Data", getDataHot.toString())

            withContext(Dispatchers.Main){
                viewModelHot.HotData(getDataHot)

                viewModelHot.dataHotLive.observe(this@FirsFragment, Observer { it->
                    Log.d("logfragfirst_it",it.toString())
                    bindingFirstFrag.rvFragfirst.layoutManager = LinearLayoutManager(context)
                    bindingFirstFrag.rvFragfirst.adapter = RecyclerAdapterHot(it)
                })
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFirstFrag = FragmentFirsBinding.inflate(inflater, container, false)
        return bindingFirstFrag.root
    }
}