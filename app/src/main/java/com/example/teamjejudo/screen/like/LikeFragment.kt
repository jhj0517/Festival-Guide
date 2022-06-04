package com.example.teamjejudo.screen.like

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamjejudo.R
import com.example.teamjejudo.adapter.LikeFestivalAdapter
import com.example.teamjejudo.adapter.LikePlaceAdapter
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.FragmentFestivalBinding
import com.example.teamjejudo.databinding.FragmentLikeBinding
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.likeFestivalDB
import com.example.teamjejudo.likePlaceDB
import com.example.teamjejudo.room.LikeFestival
import com.example.teamjejudo.room.LikePlace
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

lateinit var lrv: RecyclerView
lateinit var lprv : RecyclerView
class LikeFragment : Fragment() {
    private val likeFestival: MutableList<LikeFestival> = mutableListOf()
    private val likePlace : MutableList<LikePlace> = mutableListOf()
    private val likes: MutableList<Int> = mutableListOf()
    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    lateinit var progress : ProgressDialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressDialog(view.context)
        progress.setTitle("Loading")
        progress.setCancelable(false)
        progress.show()
        binding.likeRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.likeRecyclerView.setHasFixedSize(true)
        binding.likeRecyclerView.adapter = LikeFestivalAdapter(likeFestival, likes)
        binding.likeAreaRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.likeAreaRecyclerView.setHasFixedSize(true)
        binding.likeAreaRecyclerView.adapter=LikePlaceAdapter(likePlace,likes)
        lrv = binding.likeRecyclerView
        lprv=binding.likeAreaRecyclerView
        update()
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_LikeFragment_to_FIrstFragment)
        }




        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        binding.likeRecyclerView.visibility = View.VISIBLE
                        binding.likeAreaRecyclerView.visibility = View.GONE
                    }
                    1 -> {
                        binding.likeRecyclerView.visibility = View.GONE
                        binding.likeAreaRecyclerView.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        val r = Runnable {
            likePlace.clear()
            likeFestival.clear()
            likes.addAll(likeDB.dao().getAll())
            likeFestival.addAll(likeFestivalDB.dao().getAll())
            likePlace.addAll(likePlaceDB.dao().getAll())
            binding.root.post {
                binding.likeRecyclerView.adapter?.notifyDataSetChanged()
                binding.likeAreaRecyclerView.adapter?.notifyDataSetChanged()
                progress.dismiss()
            }
        }
        Thread(r).start()
    }


}