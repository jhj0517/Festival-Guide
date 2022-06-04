package com.example.teamjejudo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamjejudo.R
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.databinding.CellFestivalBinding
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.likeFestivalDB
import com.example.teamjejudo.room.Like
import com.example.teamjejudo.screen.festival.FestivalFragmentDirections
import com.example.teamjejudo.screen.festival.frv
import timber.log.Timber

class FestivalAdapter(
    private val festivalData: List<Festival.Response.Body.Items.Item>,
    context: Context,
    private val likes: MutableList<Int>
) :
    RecyclerView.Adapter<FestivalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalAdapter.ViewHolder {
        Timber.d("뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더뷸홀더")
        val binding =
            CellFestivalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FestivalAdapter.ViewHolder, position: Int) {
        holder.bind(festivalData[position], likes)
    }

    override fun getItemCount(): Int = festivalData.size

    inner class ViewHolder(private val binding: CellFestivalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(festival: Festival.Response.Body.Items.Item, likes: MutableList<Int>) {
            binding.tvFestivalTitle.text = festival.title
            binding.tvFestivalArea.text = festival.addr1
            binding.tvFestivalDate.text = "${festival.eventstartdate} ~ ${festival.eventenddate}"
            Glide.with(itemView.context).load(festival.firstimage).into(binding.ivFestivalRepresent)
            if (likes.contains(festival.contentid)) {
                binding.likeFestivalButton.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                binding.likeFestivalButton.setImageResource(android.R.drawable.btn_star_big_off)
            }
            binding.likeFestivalButton.setOnClickListener {
                if (likes.contains(festival.contentid)) {
                    val r = Runnable {
                        likeDB.dao().delete(festival.contentid)
                        likeFestivalDB.dao().delete(festival.contentid)
                        val db = likeDB.dao().getAll()
                        likes.clear()
                        likes.addAll(db)
                        it.post {
                            frv.adapter?.notifyDataSetChanged()
                        }
                    }
                    Thread(r).start()
                } else {
                    val r = Runnable {
                        likeDB.dao().insert(Like(festival.contentid))
                        likeFestivalDB.dao().insert(festival.toEntity())
                        val db = likeDB.dao().getAll()
                        likes.clear()
                        likes.addAll(db)
                        it.post {
                            frv.adapter?.notifyDataSetChanged()
                        }
                    }
                    Thread(r).start()
                }
            }
            itemView.setOnClickListener {
                it.findNavController().navigate(FestivalFragmentDirections.actionFirstFragmentToSecondFragment(festival.contentid))
            }
        }
    }
}
