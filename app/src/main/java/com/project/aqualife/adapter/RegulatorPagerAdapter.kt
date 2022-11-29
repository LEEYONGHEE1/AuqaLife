package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.regulator.RegulatorFragment
import com.project.aqualife.fragment.regulator.RegulatorListFragment
import com.project.aqualife.fragment.regulator.RegulatorScheduleAddFragment

class RegulatorPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RegulatorFragment()
            1 -> RegulatorListFragment()
            2 -> RegulatorScheduleAddFragment()
            else -> RegulatorFragment()
        }
    }
}