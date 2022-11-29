package com.project.aqualife.fragment.filtration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.aqualife.MainActivity
import com.project.aqualife.databinding.FiltrationFragmentBinding
import com.project.aqualife.recycler.FiltRecyclerAdapter
import com.project.aqualife.viewModel.AuthViewModel
import com.project.aqualife.viewModel.DataViewModel

class FiltrationFragment : Fragment() {
    private var binding : FiltrationFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel
    private lateinit var dataViewModel : DataViewModel

    private val recyclerAdapter : FiltRecyclerAdapter by lazy { FiltRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FiltrationFragmentBinding.inflate(inflater, container, false)

        val filtTime = binding!!.filtrationTime
        val waterCycle = binding!!.waterCycle
        val progressText = binding!!.progressState
        val progressCharge = binding!!.progressBar

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        dataViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        val recycler = binding!!.aquariumRecycler

        recyclerAdapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = recyclerAdapter

        dataViewModel.recentIndex.observe(requireActivity()){
            filtTime.text = (activity as MainActivity).aquariumList[it].filtStartTime
            var s = (activity as MainActivity).aquariumList[it].filtRemain
            var amount = (activity as MainActivity).aquariumList[it].filtAmount
            waterCycle.text = "${amount}/4환수"
            progressText.text = "${s}%"
            progressCharge.progress = s.toInt()
        }

        authViewModel.aquariumData.observe(requireActivity()) {
            recyclerAdapter.setData(it)
            progressCharge.progress = it[0].filtRemain.toInt()
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}