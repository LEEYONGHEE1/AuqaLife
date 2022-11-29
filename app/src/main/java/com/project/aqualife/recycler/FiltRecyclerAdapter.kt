package com.project.aqualife.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aqualife.data.AquariumData
import com.project.aqualife.databinding.FiltrationRecyclerItemBinding

class FiltRecyclerAdapter : RecyclerView.Adapter<FiltRecyclerAdapter.FiltViewHolder>(){
    private var valueList = ArrayList<AquariumData>()

    class FiltViewHolder(private val binding: FiltrationRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : AquariumData){
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FiltViewHolder {
        val binding = FiltrationRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FiltViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltViewHolder, position: Int) {
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