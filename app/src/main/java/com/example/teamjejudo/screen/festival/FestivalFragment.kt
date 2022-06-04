package com.example.teamjejudo.screen.festival

import android.hardware.lights.LightsManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamjejudo.R
import com.example.teamjejudo.adapter.FestivalAdapter
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.FragmentFestivalBinding
import timber.log.Timber
import java.util.ArrayList

class FestivalFragment : Fragment() {

    private var _binding: FragmentFestivalBinding? = null
    private var festival = ArrayList<Festival>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFestivalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFestivalAdapter()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun initFestivalAdapter() {
        binding.rvFestival.adapter = FestivalAdapter(festival, requireContext())
        binding.rvFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        Timber.d("sdfsdfsdfsdfsdfsdf")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}