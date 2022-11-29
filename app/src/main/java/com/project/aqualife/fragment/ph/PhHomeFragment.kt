package com.project.aqualife.fragment.ph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.aqualife.MainActivity
import com.project.aqualife.adapter.PhPagerAdapter
import com.project.aqualife.databinding.PhHomeFragmentBinding
import com.project.aqualife.fragment.OnBackPressedListener


class PhHomeFragment : Fragment() , OnBackPressedListener{
    private var binding : PhHomeFragmentBinding? = null
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhHomeFragmentBinding.inflate(inflater, container, false)

        viewPager = binding!!.phViewPager
        val phSetting = binding!!.phSetting

        viewPager.adapter = PhPagerAdapter(requireActivity())

        phSetting.setOnClickListener {
            if( viewPager.currentItem == 0)
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