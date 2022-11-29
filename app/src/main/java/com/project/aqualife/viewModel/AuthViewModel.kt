package com.project.aqualife.viewModel

import androidx.lifecycle.ViewModel
import com.project.aqualife.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val _userLiveData = authRepository.userLiveData
    val userLiveData get() = _userLiveData

    private val _aquariumData = authRepository.aquariumData
    val aquariumData get() = _aquariumData
    val fragmentData get() = _aquariumData

    // 로그인 시도
    fun tryLogin(id : String, pw : String){
        authRepository.getUserData(id, pw)
    }

    // 회원가입
    fun register(id : String, pw : String){
        authRepository.register(id, pw)
    }

    // 어항 데이터 가져오기
    fun getAquariumData(){
        authRepository.getAquariumData()
    }

    // 장치 On/Off 상태 변경
    fun changeState(name : String, part : String, state : String){
        authRepository.changeState(name, part, state)
    }

    // 스케쥴 추가하기
    fun addSchedule(aquariumName: String, scheduleName: String, part: String, start : String, end : String, state: String){
        authRepository.addSchedule(aquariumName, scheduleName, part, start, end, state)
    }

    // 스케쥴 On/Off 변경
    fun scheduleControl(aquariumName : String, scheduleName : String, part : String, state : String){
        authRepository.scheduleControl(aquariumName, scheduleName, part, state)
    }

    // 온도 고정값 변경
    fun changeHolding(name : String, value : Int){
        authRepository.changeHolding(name, value)
    }

    fun changePhSetting(name : String, Maxvalue : Float, Minvalue : Float){
        authRepository.changePhSetting(name, Maxvalue, Minvalue)
    }

    fun changeFiltSetting(name : String, dayCode : String, time : String, filtCount : Int) {
        authRepository.changeFiltSetting(name, dayCode,time, filtCount)
    }

    fun logout(){
        authRepository.logout()
    }
}