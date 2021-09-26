package com.am.sgmobiledata.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.model.RecordsItem
import com.am.sgmobiledata.databinding.HomeListItemBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {
    var onItemClick: ((EntityYear, Int) -> Unit)? = null
    var aList = mutableListOf<EntityYear>()

    fun setItemList(aList: MutableList<EntityYear>) {
        this.aList = aList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeListItemBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = aList[position]
        holder.binding.yearName.text = "${position + 1}. Year: ${item.yearName}"
        holder.binding.volume.text = "Total Data: ${String.format("%.4f", item.volumePerYear)}"
        when (position % 6) {
            0 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#f0fff0"))
            1 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#fff0f5"))
            2 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#ffefd5"))
            3 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#ffe4e1"))
            4 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#e6e6fa"))
            5 -> holder.binding.itemLayout.setBackgroundColor(Color.parseColor("#fffafa"))
        }

    }

    override fun getItemCount(): Int {
        return aList.size
    }

    inner class MainViewHolder(val binding: HomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.itemRoot.setOnClickListener {
                onItemClick?.invoke(
                    aList[adapterPosition],
                    adapterPosition
                )
            }
        }
    }
}

