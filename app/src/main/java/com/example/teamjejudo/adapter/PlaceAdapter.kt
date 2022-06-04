package com.example.teamjejudo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamjejudo.R
import com.example.teamjejudo.data.AreaBased
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.likePlaceDB
import com.example.teamjejudo.room.Like
import com.example.teamjejudo.room.LikePlace
import com.example.teamjejudo.screen.festival.FestivalDetailFragmentDirections
import com.example.teamjejudo.screen.festival.nrv
import com.example.teamjejudo.screen.like.lprv

class PlaceAdapter(private val items: MutableList<AreaBased.Response.Body.Items.Item>,private val likes : MutableList<Int>) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_place, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaceAdapter.ViewHolder, position: Int) {
        holder.bind(items,items[position],likes)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private val title : TextView = itemView.findViewById(R.id.tv_place_title)
        private val add : TextView = itemView.findViewById(R.id.tv_place_area)
        private val date : TextView = itemView.findViewById(R.id.tv_place_date)
        private val image : ImageView = itemView.findViewById(R.id.iv_place_represent)
        private val likeButton : ImageButton = itemView.findViewById(R.id.likePlaceButton)
        @SuppressLint("NotifyDataSetChanged")
        fun bind(items: MutableList<AreaBased.Response.Body.Items.Item>, item: AreaBased.Response.Body.Items.Item, likes: MutableList<Int>){
            title.text=item.title
            add.text=item.addr1
            date.text=""
            Glide.with(itemView.context).load(item.firstimage).into(image)
            itemView.setOnClickListener {
                it.findNavController().navigate(FestivalDetailFragmentDirections.actionSecondFragmentSelf(item.contentid))
            }
            if(likes.contains(item.contentid)) {
                likeButton.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else {
                likeButton.setImageResource(android.R.drawable.btn_star_big_off)
            }
            likeButton.setOnClickListener {
                if(likes.contains(item.contentid)) {
                    val r = Runnable {
                        likeDB.dao().delete(item.contentid)
                        likePlaceDB.dao().delete(item.contentid)
                        likes.clear()
                        likes.addAll(likeDB.dao().getAll())
                        it.post {
                            nrv.adapter?.notifyDataSetChanged()
                        }
                    }
                    Thread(r).start()
                }
                else {
                    val r = Runnable {
                        likeDB.dao().insert(Like(item.contentid))
                        likePlaceDB.dao().insert(item.toEntity())
                        likes.clear()
                        likes.addAll(likeDB.dao().getAll())
                        it.post {
                            nrv.adapter?.notifyDataSetChanged()
                        }
                    }
                    Thread(r).start()
                }
            }
        }
    }
}