package com.example.teamjejudo.screen.festival

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.teamjejudo.R
import com.example.teamjejudo.data.FestivalDetail
import com.example.teamjejudo.databinding.FragmentFestivalDetailBinding
import com.example.teamjejudo.retrofit.Key
import com.example.teamjejudo.retrofit.RetrofitClass
import retrofit2.Call
import retrofit2.Response
import java.net.URLDecoder

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FestivalDetailFragment : Fragment() {

    private var _binding: FragmentFestivalDetailBinding? = null
    val contentId: FestivalDetailFragmentArgs by navArgs()

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
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        getDetail(num)
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
            "json"
        )
        retrofitClass.enqueue(object : retrofit2.Callback<FestivalDetail>{
            override fun onResponse(
                call: Call<FestivalDetail>,
                response: Response<FestivalDetail>
            ) {
                val r = response.body()!!.response.body.items.item
                binding.root.post {
                    binding.detailTitle.text=r.title
                    binding.detailAddr.text=r.addr1
                    binding.detailContent.text=r.overview
                    Glide.with(binding.root).load(r.firstimage).into(binding.detailImage)
                }
            }

            override fun onFailure(call: Call<FestivalDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}