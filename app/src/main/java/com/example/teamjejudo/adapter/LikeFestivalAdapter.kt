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
import com.example.teamjejudo.room.LikeFestival
import com.example.teamjejudo.room.LikeFestivalDB
import com.example.teamjejudo.screen.like.lrv

class LikeFestivalAdapter(
    private val items : MutableList<LikeFestival>,
    private val likes : MutableList<Int>
) : RecyclerView.Adapter<LikeFestivalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_festival, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items,items[position],likes)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val title : TextView = itemView.findViewById(R.id.tv_festival_title)
        private val add : TextView = itemView.findViewById(R.id.tv_festival_area)
        private val date : TextView = itemView.findViewById(R.id.tv_festival_date)
        private val image : ImageView = itemView.findViewById(R.id.iv_festival_represent)
        private val likeButton : ImageButton = itemView.findViewById(R.id.likeButton)
        @SuppressLint("NotifyDataSetChanged")
        fun bind(items: MutableList<LikeFestival>, item: LikeFestival, likes: MutableList<Int>){
            title.text=item.title
            add.text=item.addr1
            date.text="${item.eventstartdate} - ${item.eventenddate}"
            Glide.with(itemView.context).load(item.firstimage).into(image)
            likeButton.setImageResource(android.R.drawable.btn_star_big_on)
            likeButton.setOnClickListener {
                val r = Runnable {
                    likeDB.dao().delete(item.contentid!!)
                    likeFestivalDB.dao().delete(item.contentid!!)
                    likes.clear()
                    items.clear()
                    likes.addAll(likeDB.dao().getAll())
                    items.addAll(likeFestivalDB.dao().getAll())
                    it.post {
                        lrv.adapter?.notifyDataSetChanged()
                    }
                }
                Thread(r).start()
            }
        }
    }
}