package com.neo_orez.PodcastAppGraphql.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.neo_orez.PodcastAppGraphql.GridModel
import com.neo_orez.PodcastAppGraphql.R
import com.neo_orez.PodcastAppGraphql.adapters.GridAdapter
import com.neo_orez.PodcastAppGraphql.databinding.FragmentSecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList


class SecondFragment : Fragment() {
    private lateinit var bindingSecFrag: FragmentSecondBinding
    private lateinit var ListA: MutableList<GridModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ListA = ArrayList()
        ListA.add(GridModel("Arts",R.drawable.arts))
        ListA.add(GridModel("Animation",R.drawable.animation100))
        ListA.add(GridModel("Books",R.drawable.book))
        ListA.add(GridModel("Business",R.drawable.business))
        ListA.add(GridModel("Education",R.drawable.education))
        ListA.add(GridModel("Games",R.drawable.game))
        ListA.add(GridModel("Comedy",R.drawable.comedy))
        ListA.add(GridModel("Kids",R.drawable.kid))
        ListA.add(GridModel("Music",R.drawable.music))
        ListA.add(GridModel("Religion",R.drawable.religion))
        ListA.add(GridModel("Society",R.drawable.society))
        ListA.add(GridModel("Sports",R.drawable.sports))
        ListA.add(GridModel("Technology",R.drawable.technology))
        ListA.add(GridModel("Travel",R.drawable.travel))


        GlobalScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main){
                bindingSecFrag.gridViewID.adapter = GridAdapter( ListA, requireContext())
                bindingSecFrag.gridViewID.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        Toast.makeText(requireContext(), ListA[position].gridText + " selected", Toast.LENGTH_SHORT)
                            .show()
                       val txt = ListA[position].gridText

                        val bundle = Bundle()
                        bundle.putString("0",txt)
                        findNavController().navigate(R.id.action_secondFragment_to_categoryFragment,bundle)

                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingSecFrag = FragmentSecondBinding.inflate(inflater, container, false)
        return bindingSecFrag.root
    }
}