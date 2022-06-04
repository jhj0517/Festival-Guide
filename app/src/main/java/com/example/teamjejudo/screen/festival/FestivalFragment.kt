package com.example.teamjejudo.screen.festival

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.hardware.lights.LightsManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.teamjejudo.R
import com.example.teamjejudo.adapter.FestivalAdapter
import com.example.teamjejudo.adapter.SearchAdapter
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
    lateinit var progress : ProgressDialog
    private val adapter = SearchAdapter(festival)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFestivalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressDialog(view.context)
        progress.setTitle("Loading")
        progress.setCancelable(false)
        progress.show()
        getFestival()
        initFestivalAdapter()
        frv = binding.rvFestival
        binding.goLikeFragment.setOnClickListener {
            findNavController().navigate(R.id.action_FIrstFragment_to_LikeFragment)
        }
        binding.searchRecyclerView.layoutManager=LinearLayoutManager(view.context)
        binding.searchRecyclerView.setHasFixedSize(true)
        binding.searchRecyclerView.adapter=adapter

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

        binding.searchFestival.setOnFocusChangeListener{ v, focus ->
            if(focus){

                binding.searchRecyclerView.visibility = View.VISIBLE
            }
            else {
                binding.searchRecyclerView.visibility = View.GONE
            }
        }
        binding.searchFestival.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

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
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        Timber.d("sdfsdfsdfsdfsdfsdf")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun getFestival(){
        val retrofit = RetrofitClass().api.getFestivals(URLDecoder.decode(Key,"UTF-8"),"AND","App","20220604",1000,"json")
        retrofit.enqueue(object : retrofit2.Callback<Festival>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Festival>, response: Response<Festival>) {
                festival.addAll(response.body()!!.response.body.items.item)
                binding.rvFestival.adapter?.notifyDataSetChanged()
                adapter.submitList(festival)
                progress.dismiss()
            }

            override fun onFailure(call: Call<Festival>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}