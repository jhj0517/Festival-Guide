package com.example.teamjejudo.screen.festival

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamjejudo.R
import com.example.teamjejudo.adapter.PlaceAdapter
import com.example.teamjejudo.data.AreaBased
import com.example.teamjejudo.data.FestivalDetail
import com.example.teamjejudo.databinding.FragmentFestivalDetailBinding
import com.example.teamjejudo.retrofit.Key
import com.example.teamjejudo.retrofit.RetrofitClass
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Response
import java.net.URLDecoder

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FestivalDetailFragment : Fragment() {

    private var _binding: FragmentFestivalDetailBinding? = null
    val contentId: FestivalDetailFragmentArgs by navArgs()
    private val place = mutableListOf<AreaBased.Response.Body.Items.Item>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFestivalDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var num = contentId.contentId
        binding.buttonSecond.setOnClickListener {
            findNavController().popBackStack()
        }
        getDetail(num)
        binding.nearPlaceRV.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.nearPlaceRV.setHasFixedSize(true)
        binding.nearPlaceRV.adapter = PlaceAdapter(place)
        binding.detailTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        binding.detailContent.visibility = View.VISIBLE
                        binding.view3.visibility = View.VISIBLE
                        binding.textView4.visibility = View.VISIBLE
                        binding.nearPlaceRV.visibility = View.GONE
                    }
                    1 -> {
                        binding.detailContent.visibility = View.GONE
                        binding.view3.visibility = View.GONE
                        binding.textView4.visibility = View.GONE
                        binding.nearPlaceRV.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.nearPlaceRV)
        binding.nearPlaceRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDetail(num: Int) {
        val retrofitClass = RetrofitClass().api.getDetail(
            URLDecoder.decode(Key, "UTF-8"),
            "AND",
            "App",
            num,
            "Y",
            "Y",
            "Y",
            "Y",
            "Y",
            "json"
        )
        retrofitClass.enqueue(object : retrofit2.Callback<FestivalDetail> {
            override fun onResponse(
                call: Call<FestivalDetail>,
                response: Response<FestivalDetail>
            ) {
                val r = response.body()!!.response.body.items.item
                binding.root.post {
                    binding.detailTitle.text = r.title
                    binding.detailAddr.text = r.addr1
                    binding.detailContent.text = r.overview.split("<br")[0]
                    if (r.firstimage != "") {
                        Glide.with(binding.root).load(r.firstimage).into(binding.detailImage)
                    }
                }
                println(r.contentid)
                getNear(r.areacode)

            }

            override fun onFailure(call: Call<FestivalDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun getNear(num: Int) {
        val retrofitClass = RetrofitClass().api.getNearPlace(
            URLDecoder.decode(Key, "UTF-8"),
            "AND",
            "App",
            num,
            12,
            10,
            "json"
        )
        retrofitClass.enqueue(object : retrofit2.Callback<AreaBased> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<AreaBased>, response: Response<AreaBased>) {
                place.addAll(response.body()!!.response.body.items.item)
                binding.nearPlaceRV.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<AreaBased>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}