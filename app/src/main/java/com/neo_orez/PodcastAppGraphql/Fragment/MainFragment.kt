package com.neo_orez.PodcastAppGraphql.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.neo_orez.PodcastAppGraphql.Apollo.CallRequest
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {

    lateinit var bindingMainFrag : FragmentMainBinding
    lateinit var viewModel: MainFragViewModel
    private var factory = MainViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,factory).get(MainFragViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getToken = CallRequest().apolloToken()
            val getData = CallRequest().apolloData(getToken)
            Log.d("apollo1", getData.toString())

            withContext(Dispatchers.Main){
                bindingMainFrag.rvRecyclerView.layoutManager = LinearLayoutManager(context)
                //viewModel.firstList.observe(this@MainFragment, Observer {
                 //   bindingMainFrag.rvRecyclerView.adapter = RecyclerAdapter(getData) })
                bindingMainFrag.rvRecyclerView.adapter = RecyclerAdapter(getData)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingMainFrag = FragmentMainBinding.inflate(inflater,container,false)
        return  bindingMainFrag.root
    }
}