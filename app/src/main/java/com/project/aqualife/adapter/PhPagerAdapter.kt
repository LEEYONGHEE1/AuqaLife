package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.ph.PhFragment
import com.project.aqualife.fragment.ph.PhSettingFragment

class PhPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PhFragment()
            1 -> PhSettingFragment()
            else -> PhFragment()
        }
    }
}