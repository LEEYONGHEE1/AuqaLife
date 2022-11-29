package com.project.aqualife.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aqualife.data.AquariumData
import com.project.aqualife.databinding.PhRecyclerItemBinding

class PhRecyclerAdapter : RecyclerView.Adapter<PhRecyclerAdapter.PhViewHolder>(){
    private var valueList = ArrayList<AquariumData>()

    class PhViewHolder(private val binding: PhRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : AquariumData){
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PhViewHolder {
        val binding = PhRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhViewHolder, position: Int) {
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