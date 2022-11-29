package com.project.aqualife.fragment.light

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
import com.project.aqualife.databinding.LightFragmentBinding
import com.project.aqualife.recycler.LightRecyclerAdapter
import com.project.aqualife.viewModel.AuthViewModel
import com.project.aqualife.viewModel.DataViewModel

class LightFragment : Fragment() {
    private var binding : LightFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel
    private lateinit var dataViewModel : DataViewModel
    private val recyclerAdapter : LightRecyclerAdapter by lazy{ LightRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LightFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        dataViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]

        val recycler = binding!!.aquariumRecycler
        val lightOn = binding!!.lightOn
        val lightOff = binding!!.lightOff
        val light = binding!!.lightBulb

        lightOn.setOnClickListener {
            light.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.mYellow))
            authViewModel.changeState((activity as MainActivity).getSpinnerData().first, "light", "on")
        }

        lightOff.setOnClickListener {
            light.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.gray))
            authViewModel.changeState((activity as MainActivity).getSpinnerData().first, "light", "off")
        }

        // recycler view 설정
        recyclerAdapter.setHasStableIds(true)
        recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = recyclerAdapter

        // Aquarium 스피너 선택시 데이터 현재 On/Off 상태 시각화
        dataViewModel.recentIndex.observe(requireActivity()){
            val state = (activity as MainActivity).aquariumList[it].lightState
            if(state == "on")
                light.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.mYellow))
            else
                light.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.gray))
        }

        // 데이터 변경시 recycler view 갱신
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