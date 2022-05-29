package com.riyaspullur.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewAdapter:PagingDataAdapter<CharacterData,RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    class MyViewHolder (view:View):RecyclerView.ViewHolder(view){

        val imageView=view.imageViewID
        val tvName=view.tvName
        val tvDesc=view.tvDesc
        fun bind(data:CharacterData){
            tvName.text=data.name
            tvDesc.text=data.species


            Glide.with(imageView)
                .load(data.image)
                .circleCrop()
                .into(imageView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerViewAdapter.MyViewHolder{
       val inflater=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return MyViewHolder(inflater)
    }

    class DiffUtilCallBack:DiffUtil.ItemCallback<CharacterData>(){
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
           return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name== newItem.name
                    && oldItem.species==newItem.species
        }

    }
}