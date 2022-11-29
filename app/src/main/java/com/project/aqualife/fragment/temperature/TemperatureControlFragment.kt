package com.project.aqualife.fragment.temperature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.aqualife.MainActivity
import com.project.aqualife.databinding.TemperatureControlFragmentBinding
import com.project.aqualife.viewModel.AuthViewModel

class TemperatureControlFragment : Fragment() {
    private var binding : TemperatureControlFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TemperatureControlFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val numberPicker = binding!!.holdingTemperature
        val set = binding!!.configSetting

        numberPicker.maxValue = 100
        numberPicker.minValue = 0
        numberPicker.value = 27

        set.setOnClickListener {
            authViewModel.changeHolding((activity as MainActivity).getSpinnerData().first, numberPicker.value)
            (activity as MainActivity).onBackPressed()
            val temp = numberPicker.value
            Toast.makeText(context, "온도가 설정되었습니다.($temp℃)", Toast.LENGTH_SHORT).show()
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}