package com.sebs.bodybuildinglegends.ui

import androidx.recyclerview.widget.RecyclerView
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import com.sebs.bodybuildinglegends.databinding.ActivityMainBinding
import com.sebs.bodybuildinglegends.databinding.LegendElementBinding

class LegendViewHolder(

    private val binding: LegendElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bin(legend: LegendEntity){


        binding.apply {
            tvTitle.text = legend.title
            tvCategory.text = legend.category
            tvRegion.text = legend.region
        }

    }

}

