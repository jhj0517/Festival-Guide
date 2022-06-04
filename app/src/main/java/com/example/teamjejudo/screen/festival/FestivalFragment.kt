package com.example.teamjejudo.screen.festival

import android.annotation.SuppressLint
import android.hardware.lights.LightsManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.teamjejudo.R
import com.example.teamjejudo.adapter.FestivalAdapter
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.FragmentFestivalBinding
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.retrofit.Key
import com.example.teamjejudo.retrofit.RetrofitClass
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.net.URLDecoder
import java.util.ArrayList

lateinit var frv : RecyclerView
class FestivalFragment : Fragment() {

    private var _binding: FragmentFestivalBinding? = null
    private var festival = ArrayList<Festival.Response.Body.Items.Item>()
    private var likes : MutableList<Int> = mutableListOf()

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
        getFestival()
        initFestivalAdapter()
        frv = binding.rvFestival
        binding.goLikeFragment.setOnClickListener {
            findNavController().navigate(R.id.action_FIrstFragment_to_LikeFragment)

        }
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(frv)
        frv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var currentPosition = RecyclerView.NO_POSITION

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(recyclerView.layoutManager!=null){
                    val view = snapHelper.findSnapView(recyclerView.layoutManager)!!
                    val position = recyclerView.layoutManager!!.getPosition(view)

                    if (currentPosition!= position){
                        currentPosition = position
                    }
                }
            }
        })


    }

    private fun initFestivalAdapter() {
        val r = Runnable {
            likes.addAll(likeDB.dao().getAll())
        }
        Thread(r).start()
        binding.rvFestival.adapter = FestivalAdapter(festival, requireContext(), likes)
        binding.rvFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        Timber.d("sdfsdfsdfsdfsdfsdf")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFestival(){
        val retrofit = RetrofitClass().api.getFestivals(URLDecoder.decode(Key,"UTF-8"),"AND","App","20220604","json")
        retrofit.enqueue(object : retrofit2.Callback<Festival>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Festival>, response: Response<Festival>) {
                festival.addAll(response.body()!!.response.body.items.item)
                binding.rvFestival.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Festival>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}