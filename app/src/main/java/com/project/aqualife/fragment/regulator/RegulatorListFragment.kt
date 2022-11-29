package com.project.aqualife.fragment.regulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.aqualife.MainActivity
import com.project.aqualife.databinding.RegulatorListFragmentBinding
import com.project.aqualife.recycler.TemperatureRegulatorScheduleRecyclerAdapter
import com.project.aqualife.viewModel.AuthViewModel
import com.project.aqualife.viewModel.DataViewModel

class RegulatorListFragment : Fragment() {
    private var binding : RegulatorListFragmentBinding? = null
    private val recyclerAdapter : TemperatureRegulatorScheduleRecyclerAdapter by lazy{ TemperatureRegulatorScheduleRecyclerAdapter(authViewModel) }
    private lateinit var authViewModel : AuthViewModel
    private lateinit var dataViewModel : DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegulatorListFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        dataViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        val recycler = binding!!.aquariumRecycler

        recyclerAdapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = recyclerAdapter

        // Aquarium 스피너 선택시 선택한 어항의 예약 목록으로 최신화
        dataViewModel.recentIndex.observe(requireActivity()){
            recyclerAdapter.setData((activity as MainActivity).aquariumList[it].co2Reservation)
        }

        // 데이터 변경시 recycler view 갱신
        authViewModel.aquariumData.observe(requireActivity()){
            recyclerAdapter.setData(it[dataViewModel.recentIndex.value!!.toInt()].co2Reservation)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}