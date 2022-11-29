package com.project.aqualife.fragment.ph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.aqualife.MainActivity
import com.project.aqualife.databinding.PhSettingFragmentBinding
import com.project.aqualife.viewModel.AuthViewModel

class PhSettingFragment : Fragment() {

    private var binding : PhSettingFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhSettingFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val numberPickerF1 = binding!!.firstNumber1
        val numberPickerF2 = binding!!.firstNumber2
        val numberPickerS1 = binding!!.secondNumber1
        val numberPickerS2 = binding!!.secondNumber2
        val set = binding!!.settingPh


        numberPickerF1.maxValue = 14
        numberPickerF1.minValue = 0
        numberPickerF1.value = 6

        numberPickerS1.maxValue = 9
        numberPickerS1.minValue = 0
        numberPickerS1.value = 5

        numberPickerF2.maxValue = 14
        numberPickerF2.minValue = 0
        numberPickerF2.value = 7

        numberPickerS2.maxValue = 9
        numberPickerS2.minValue = 0
        numberPickerS2.value = 0

        set.setOnClickListener {
            val numberF1 = numberPickerF1.value
            val numberS1 = numberPickerS1.value
            val numberF2 = numberPickerF2.value
            val numberS2 = numberPickerS2.value

            if (numberF1 >= numberF2 && numberS1 >= numberS2) {
                Toast.makeText(context, ".PH 설정 값 오류!! 다시 설정하세요.", Toast.LENGTH_LONG).show()
            }else {
                val MinValue = "${numberF1}.${numberS1}".toFloat()
                val MaxValue = "${numberF2}.${numberS2}".toFloat()
                authViewModel.changePhSetting((activity as MainActivity).getSpinnerData().first, MaxValue, MinValue)
                (activity as MainActivity).onBackPressed()

                Toast.makeText(context, ".PH 설정 완료$MinValue ~ $MaxValue", Toast.LENGTH_LONG).show()
            }
        }

        return binding!!.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}