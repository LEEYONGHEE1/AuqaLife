package com.project.aqualife.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.aqualife.fragment.filtration.FiltrationCalFragment
import com.project.aqualife.fragment.filtration.FiltrationFragment
import com.project.aqualife.fragment.filtration.FiltrationSettingFragment

class FiltrationPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FiltrationFragment()
            1 -> FiltrationCalFragment()
            2 -> FiltrationSettingFragment()
            else -> FiltrationFragment()
        }
    }
}