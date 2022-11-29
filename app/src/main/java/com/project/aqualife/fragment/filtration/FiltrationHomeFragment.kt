package com.project.aqualife.fragment.filtration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.aqualife.MainActivity
import com.project.aqualife.adapter.FiltrationPagerAdapter
import com.project.aqualife.databinding.FiltrationHomeFragmentBinding
import com.project.aqualife.fragment.OnBackPressedListener


class FiltrationHomeFragment : Fragment() , OnBackPressedListener {
    private var binding : FiltrationHomeFragmentBinding? = null
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FiltrationHomeFragmentBinding.inflate(inflater, container, false)

        viewPager = binding!!.filtViewPager
        val ftSetting = binding!!.filtSetting

        viewPager.adapter = FiltrationPagerAdapter(requireActivity())

        ftSetting.setOnClickListener {
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
        viewPager.currentItem = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}