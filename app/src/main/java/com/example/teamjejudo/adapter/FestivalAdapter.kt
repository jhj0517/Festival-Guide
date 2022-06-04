package com.example.teamjejudo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.CellFestivalBinding
import timber.log.Timber

class FestivalAdapter(private val festivalData: List<Festival.Response.Body.Items.Item>, context: Context) :
    RecyclerView.Adapter<FestivalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalAdapter.ViewHolder {
        Timber.d("뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더")
        val binding =
            CellFestivalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FestivalAdapter.ViewHolder, position: Int) {
        holder.bind(festivalData[position])
    }

    override fun getItemCount(): Int = festivalData.size

    inner class ViewHolder(private val binding: CellFestivalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(festival: Festival.Response.Body.Items.Item) {
            binding.tvFestivalTitle.text = festival.title
            binding.tvFestivalArea.text = festival.addr1
            binding.tvFestivalDate.text = "${festival.eventstartdate} ~ ${festival.eventenddate}"
            Glide.with(itemView.context).load(festival.firstimage).into(binding.ivFestivalRepresent)
        }
    }
}
