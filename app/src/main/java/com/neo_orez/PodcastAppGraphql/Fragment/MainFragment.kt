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
import com.neo_orez.PodcastAppGraphql.ViewModel.MainFragViewModel
import com.neo_orez.PodcastAppGraphql.ViewModel.MainViewModelFactory
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {
    lateinit var bindingMainFrag : FragmentMainBinding
    lateinit var viewModel : MainFragViewModel
    var factory = MainViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(MainFragViewModel::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val getToken = CallRequest().apolloToken()
            val getData = CallRequest().apolloDataFarsi(getToken)
            Log.d("logfrag_Data",getData.toString())

            withContext(Dispatchers.Main){
                viewModel.xData(getData)
                viewModel.dataListLive.observe(this@MainFragment, Observer {it->
                    Log.d("logfrag_it",it.toString())
                    bindingMainFrag.rvFragmain.layoutManager = LinearLayoutManager(context)
                    bindingMainFrag.rvFragmain.adapter = RecyclerAdapter(it)
                })
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingMainFrag = FragmentMainBinding.inflate(inflater,container,false)
        return  bindingMainFrag.root
    }
}