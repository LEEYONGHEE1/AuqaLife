package com.project.aqualife.fragment.regulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.aqualife.MainActivity
import com.project.aqualife.R
import com.project.aqualife.databinding.RegulatorFragmentBinding
import com.project.aqualife.recycler.RegulatorRecyclerAdapter
import com.project.aqualife.viewModel.DataViewModel
import com.project.aqualife.viewModel.AuthViewModel

class RegulatorFragment : Fragment() {
    private var binding : RegulatorFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel
    private lateinit var dataViewModel : DataViewModel
    private val recyclerAdapter : RegulatorRecyclerAdapter by lazy{ RegulatorRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegulatorFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        dataViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        val recycler = binding!!.aquariumRecycler
        val regulator = binding!!.regulatorText
        val regulatorOn = binding!!.regulatorOn
        val regulatorOff = binding!!.regulatorOff
        val text = binding!!.regulatorText

        regulatorOn.setOnClickListener {
            text.setTextColor(ContextCompat.getColor(requireActivity(), R.color.mYellow))
            authViewModel.changeState((activity as MainActivity).getSpinnerData().first, "co2", "on")
        }

        regulatorOff.setOnClickListener {
            text.setTextColor(ContextCompat.getColor(requireActivity(), R.color.gray))
            authViewModel.changeState((activity as MainActivity).getSpinnerData().first, "co2", "off")
        }

        // recycler view ??????
        recyclerAdapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = recyclerAdapter

        // Aquarium ????????? ????????? ????????? ?????? On/Off ?????? ?????????
        dataViewModel.recentIndex.observe(requireActivity()){
            val state = (activity as MainActivity).aquariumList[it].co2State
            if(state == "on")
                regulator.setTextColor(ContextCompat.getColor(requireActivity(), R.color.mYellow))
            else
                regulator.setTextColor(ContextCompat.getColor(requireActivity(), R.color.gray))
        }

        // ????????? ????????? recycler view ??????
        authViewModel.aquariumData.observe(requireActivity()){
            recyclerAdapter.setData(it)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}