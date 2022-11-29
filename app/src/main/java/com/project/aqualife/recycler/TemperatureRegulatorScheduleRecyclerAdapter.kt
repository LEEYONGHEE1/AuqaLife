package com.project.aqualife.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aqualife.data.TemperatureRegulatorSchedule
import com.project.aqualife.databinding.TempRegulScheduleRecyclerBinding
import com.project.aqualife.viewModel.AuthViewModel

class TemperatureRegulatorScheduleRecyclerAdapter(private val viewModel : AuthViewModel) : RecyclerView.Adapter<TemperatureRegulatorScheduleRecyclerAdapter.RegulatorScheduleViewHolder>(){
    private var valueList = ArrayList<TemperatureRegulatorSchedule>()

    class RegulatorScheduleViewHolder(private val binding : TempRegulScheduleRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var viewModel : AuthViewModel
        fun bind(data : TemperatureRegulatorSchedule, viewModel : AuthViewModel){
            binding.data = data
            this.viewModel = viewModel

            binding.powerControl.isChecked = data.state == "on"
            binding.powerControl.setOnCheckedChangeListener { _, value ->
                var type = ""

                if(data.type == "co2")
                    type = "co2"
                else if(data.type == "light")
                    type = "light"

                if(value)
                    viewModel.scheduleControl(data.aquariumName, data.name, type, "on")
                else
                    viewModel.scheduleControl(data.aquariumName, data.name, type, "off")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegulatorScheduleViewHolder {
        val binding = TempRegulScheduleRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegulatorScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegulatorScheduleViewHolder, position: Int) {
        holder.bind(valueList[position], viewModel)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : ArrayList<TemperatureRegulatorSchedule>){
        valueList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return valueList.size
    }
}