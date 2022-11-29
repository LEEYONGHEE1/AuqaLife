package com.project.aqualife.fragment.temperature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.aqualife.MainActivity
import com.project.aqualife.adapter.TemperaturePagerAdapter
import com.project.aqualife.databinding.TemperatureHomeFragmentBinding
import com.project.aqualife.fragment.OnBackPressedListener

class TemperatureHomeFragment : Fragment(), OnBackPressedListener {
    private var binding : TemperatureHomeFragmentBinding? = null
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TemperatureHomeFragmentBinding.inflate(inflater, container, false)

        viewPager = binding!!.temperatureViewPager
        val control = binding!!.temperatureControl

        viewPager.adapter = TemperaturePagerAdapter(requireActivity())

        // 플로팅 버튼 클릭 이벤트
        control.setOnClickListener {
            if (viewPager.currentItem == 0)
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