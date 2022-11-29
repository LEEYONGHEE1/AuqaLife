package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.light.*

class LightPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LightFragment()
            1 -> LightListFragment()
            2 -> LightScheduleAddFragment()
            else -> LightFragment()
        }
    }
}