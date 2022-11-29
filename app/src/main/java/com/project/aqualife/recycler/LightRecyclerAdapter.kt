package com.project.aqualife.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aqualife.data.AquariumData
import com.project.aqualife.databinding.LightRecyclerItemBinding

class LightRecyclerAdapter : RecyclerView.Adapter<LightRecyclerAdapter.LightViewHolder>(){
    private var valueList = ArrayList<AquariumData>()

    class LightViewHolder(private val binding : LightRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : AquariumData){
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightViewHolder {
        val binding = LightRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LightViewHolder, position: Int) {
        holder.bind(valueList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : ArrayList<AquariumData>){
        valueList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return valueList.size
    }
}