package com.sebs.bodybuildinglegends.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import com.sebs.bodybuildinglegends.databinding.LegendElementBinding

class LegendAdapter(): RecyclerView.Adapter<LegendViewHolder>() {

    private var legend: MutableList<LegendEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegendViewHolder {
       val binding = LegendElementBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return LegendViewHolder(binding)
    }

    override fun getItemCount(): Int = legend.size


    override fun onBindViewHolder(holder: LegendViewHolder, position: Int) {

        val legend = legend[position]

        holder.bin(legend)

        //CLICK

        holder.itemView.setOnClickListener {


        }


    }

    fun updatedList(list: MutableList<LegendEntity>){

        legend = list
        notifyDataSetChanged()

    }


}


