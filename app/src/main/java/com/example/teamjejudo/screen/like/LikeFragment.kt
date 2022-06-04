package com.example.teamjejudo.screen.like

import android.annotation.SuppressLint
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
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.FragmentFestivalBinding
import com.example.teamjejudo.databinding.FragmentLikeBinding
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.likeFestivalDB
import com.example.teamjejudo.room.LikeFestival
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LikeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

lateinit var lrv : RecyclerView
class LikeFragment : Fragment() {
    private val likeFestival : MutableList<LikeFestival> = mutableListOf()
    private val likes : MutableList<Int> = mutableListOf()
    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.likeRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.likeRecyclerView.setHasFixedSize(true)
        binding.likeRecyclerView.adapter=LikeFestivalAdapter(likeFestival,likes)
        lrv = binding.likeRecyclerView
        update()
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_LikeFragment_to_FIrstFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    fun update(){
        val r= Runnable {
            likes.addAll(likeDB.dao().getAll())
            likeFestival.addAll(likeFestivalDB.dao().getAll())
            binding.root.post {
                binding.likeRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
        Thread(r).start()
    }


}