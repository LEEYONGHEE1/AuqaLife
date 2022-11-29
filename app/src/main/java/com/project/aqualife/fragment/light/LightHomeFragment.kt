package com.project.aqualife.fragment.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.aqualife.MainActivity
import com.project.aqualife.adapter.LightPagerAdapter
import com.project.aqualife.databinding.LightHomeFragmentBinding
import com.project.aqualife.fragment.OnBackPressedListener

class LightHomeFragment : Fragment(), OnBackPressedListener {
    private var binding : LightHomeFragmentBinding? = null
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LightHomeFragmentBinding.inflate(inflater, container, false)

        viewPager = binding!!.lightViewPager
        val addSchedule = binding!!.lightAddSchedule

        viewPager.adapter = LightPagerAdapter(requireActivity())

        // 플로팅 버튼 클릭 이벤트
        addSchedule.setOnClickListener {
            if (viewPager.currentItem == 1)
                viewPager.currentItem = 2
            else
                viewPager.currentItem = 1
        }


        return binding!!.root
    }

    override fun onBackPressed() {
        if(viewPager.currentItem != 0)
            viewPager.currentItem = viewPager.currentItem - 1
        else
            (activity as MainActivity).viewPager?.currentItem = 1
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setOnBackPressedListener(this)
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).setOnBackPressedListener(null)
        viewPager.currentItem = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}