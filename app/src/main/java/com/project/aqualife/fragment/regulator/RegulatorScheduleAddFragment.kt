package com.project.aqualife.fragment.regulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.aqualife.MainActivity
import com.project.aqualife.databinding.RegulatorScheduleAddFragmentBinding
import com.project.aqualife.viewModel.AuthViewModel

class RegulatorScheduleAddFragment : Fragment() {
    private var binding : RegulatorScheduleAddFragmentBinding? = null
    private lateinit var authViewModel : AuthViewModel
    private var startTime = ""
    private var endTime = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegulatorScheduleAddFragmentBinding.inflate(inflater, container, false)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val add = binding!!.addSchedule
        val start = binding!!.startTime
        val end = binding!!.endTime

        add.setOnClickListener {
            if(startTime.isNotBlank() && endTime.isNotBlank()){
                val sb = StringBuilder()
                startTime.forEach {
                    if(it != ':')
                        sb.append(it)
                }
                endTime.forEach {
                    if(it != ':')
                        sb.append(it)
                }
                authViewModel.addSchedule((activity as MainActivity).getSpinnerData().first, sb.toString(), "co2", startTime, endTime, "off")
                (activity as MainActivity).onBackPressed()
                Toast.makeText(context, "Co2 레귤레이터 일정이 추가되었습니다.", Toast.LENGTH_SHORT).show()
            }else if(startTime.isBlank())
                Toast.makeText(context, "시작 시간을 설정하세요.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "종료 시간을 설정하세요.", Toast.LENGTH_SHORT).show()
        }

        start.setOnTimeChangedListener { _, hour, minute ->
            startTime = "$hour:$minute"
        }

        end.setOnTimeChangedListener { _, hour, minute ->
            endTime = "$hour:$minute"
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}