package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.temperature.TemperatureControlFragment
import com.project.aqualife.fragment.temperature.TemperatureFragment

class TemperaturePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TemperatureFragment()
            1 -> TemperatureControlFragment()
            else -> TemperatureFragment()
        }
    }
}