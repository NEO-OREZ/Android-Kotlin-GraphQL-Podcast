package com.neo_orez.PodcastAppGraphql

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {

    lateinit var bindingMainFrag : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            val GetToken = CallRequest().CallApollo()
            val GetData = CallRequest().ApolloData(GetToken)
            Log.d("apollo1", GetData.toString())

            withContext(Dispatchers.Main){
                bindingMainFrag.rvRecyclerView.layoutManager = LinearLayoutManager(context)
                bindingMainFrag.rvRecyclerView.adapter = RecyclerAdapter(GetData)

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingMainFrag = FragmentMainBinding.inflate(inflater,container,false)
        val view = bindingMainFrag.root
        return view
    }
}