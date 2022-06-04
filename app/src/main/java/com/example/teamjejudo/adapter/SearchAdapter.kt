package com.example.teamjejudo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teamjejudo.R
import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.screen.festival.FestivalFragmentDirections


class SearchAdapter(private val items : List<Festival.Response.Body.Items.Item>) : ListAdapter<Festival.Response.Body.Items.Item, SearchAdapter.ViewHolder>(diffUtil), Filterable {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_festival_forsearch, parent, false)
        return SearchAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Festival.Response.Body.Items.Item>() {
            override fun areContentsTheSame(oldItem: Festival.Response.Body.Items.Item, newItem: Festival.Response.Body.Items.Item) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Festival.Response.Body.Items.Item, newItem: Festival.Response.Body.Items.Item) =
                oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return SearchFilter
    }

    class ViewHolder(v : View): RecyclerView.ViewHolder(v){
        private val title : TextView = itemView.findViewById(R.id.search_title)
        fun bind(item : Festival.Response.Body.Items.Item){
            title.text=item.title
            itemView.setOnClickListener {
                it.findNavController().navigate(FestivalFragmentDirections.actionFirstFragmentToSecondFragment(item.contentid))
            }
        }
    }

    private val SearchFilter : Filter = object  : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList : ArrayList<Festival.Response.Body.Items.Item> = ArrayList()
            if(constraint==null || constraint.isEmpty()){
                filteredList.addAll(items)
            }else{
                val filterPattern = constraint.toString().lowercase().trim{it<= ' '}

                for(item in items){
                    if (item.title.contains(filterPattern)){
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            println(results?.values)
            submitList(results?.values as MutableList<Festival.Response.Body.Items.Item>)
        }

    }

}