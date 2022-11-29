package com.project.aqualife.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.aqualife.MainActivity
import com.project.aqualife.R
import com.project.aqualife.databinding.HomeFragmentBinding
import com.project.aqualife.viewModel.DataViewModel
import com.project.aqualife.viewModel.AuthViewModel

class HomeFragment :Fragment(){
    private var binding : HomeFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel
    private lateinit var dataViewModel : DataViewModel
    private var recentAquariumIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        dataViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        val temperature = binding!!.temperature
        val filtration = binding!!.filtration
        val light = binding!!.light
        val co2 = binding!!.co2
        val ph = binding!!.ph

        ph.setOnClickListener {
            (activity as MainActivity).viewPager?.currentItem = 0
        }

        filtration.setOnClickListener {
            (activity as MainActivity).viewPager?.currentItem = 2
        }

        light.setOnClickListener {
            (activity as MainActivity).viewPager?.currentItem = 3
        }

        co2.setOnClickListener {
            (activity as MainActivity).viewPager?.currentItem = 4
        }

        temperature.setOnClickListener {
            (activity as MainActivity).viewPager?.currentItem = 5
        }

        authViewModel.fragmentData.observe(requireActivity()){
            binding?.data = it[recentAquariumIndex]
        }

        dataViewModel.recentIndex.observe(requireActivity()){
            binding?.data = (activity as MainActivity).aquariumList[it]
            recentAquariumIndex = it
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}