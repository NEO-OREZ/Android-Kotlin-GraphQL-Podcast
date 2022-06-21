package com.neo_orez.PodcastAppGraphql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.neo_orez.PodcastAppGraphql.adapters.RecyclerAdapter
import com.neo_orez.PodcastAppGraphql.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
        binding.floatingActionButtonID.setOnClickListener {
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }

        GlobalScope.launch(Dispatchers.IO) {
            val GetToken = CallRequest().CallApollo()
            val GetData = CallRequest().ApolloData(GetToken)
            Log.d("apollo1", GetData.toString())

            withContext(Dispatchers.Main){
                binding.rvRecyclerView.adapter = RecyclerAdapter(GetData)
            }
        }
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
