package com.example.teamjejudo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamjejudo.R
import com.example.teamjejudo.likeDB
import com.example.teamjejudo.likeFestivalDB
import com.example.teamjejudo.likePlaceDB
import com.example.teamjejudo.room.LikeFestival
import com.example.teamjejudo.room.LikePlace
import com.example.teamjejudo.screen.like.lprv
import com.example.teamjejudo.screen.like.lrv

class LikePlaceAdapter(private val items: MutableList<LikePlace>,private val likes : MutableList<Int>) : RecyclerView.Adapter<LikePlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikePlaceAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_place, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LikePlaceAdapter.ViewHolder, position: Int) {
        holder.bind(items,items[position],likes)
    }

    override fun getItemCount() = items.size

    class ViewHolder(v : View) : RecyclerView.ViewHolder(v){
        private val title : TextView = itemView.findViewById(R.id.tv_place_title)
        private val add : TextView = itemView.findViewById(R.id.tv_place_area)
        private val date : TextView = itemView.findViewById(R.id.tv_place_date)
        private val image : ImageView = itemView.findViewById(R.id.iv_place_represent)
        private val likeButton : ImageButton = itemView.findViewById(R.id.likePlaceButton)
        @SuppressLint("NotifyDataSetChanged")
        fun bind(items: MutableList<LikePlace>, item: LikePlace, likes: MutableList<Int>){
            title.text=item.title
            add.text=item.addr1
            date.text=""
            Glide.with(itemView.context).load(item.firstimage).into(image)
            likeButton.setImageResource(android.R.drawable.btn_star_big_on)
            likeButton.setOnClickListener {
                val r = Runnable {
                    likeDB.dao().delete(item.contentid!!)
                    likePlaceDB.dao().delete(item.contentid)
                    likes.clear()
                    items.clear()
                    likes.addAll(likeDB.dao().getAll())
                    items.addAll(likePlaceDB.dao().getAll())
                    it.post {
                        lprv.adapter?.notifyDataSetChanged()
                    }
                }
                Thread(r).start()
            }
        }
    }

}