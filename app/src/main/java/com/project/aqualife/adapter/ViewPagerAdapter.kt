package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.*
import com.project.aqualife.fragment.filtration.FiltrationFragment
import com.project.aqualife.fragment.filtration.FiltrationHomeFragment
import com.project.aqualife.fragment.light.LightHomeFragment
import com.project.aqualife.fragment.ph.PhHomeFragment
import com.project.aqualife.fragment.regulator.RegulatorHomeFragment
import com.project.aqualife.fragment.temperature.TemperatureHomeFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PhHomeFragment()
            1 -> HomeFragment()
            2 -> FiltrationHomeFragment()
            3 -> LightHomeFragment()
            4 -> RegulatorHomeFragment()
            5 -> TemperatureHomeFragment()
            else -> HomeFragment()
        }
    }
}