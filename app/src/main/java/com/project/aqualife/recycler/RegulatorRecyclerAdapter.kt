package com.project.aqualife.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aqualife.data.AquariumData
import com.project.aqualife.databinding.RegulatorRecyclerItemBinding

class RegulatorRecyclerAdapter : RecyclerView.Adapter<RegulatorRecyclerAdapter.RegulatorViewHolder>(){
    private var valueList = ArrayList<AquariumData>()

    class RegulatorViewHolder(private val binding : RegulatorRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : AquariumData){
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegulatorViewHolder {
        val binding = RegulatorRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegulatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegulatorViewHolder, position: Int) {
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