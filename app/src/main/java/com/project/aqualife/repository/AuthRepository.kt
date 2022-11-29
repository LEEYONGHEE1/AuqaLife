package com.project.aqualife.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.project.aqualife.data.AquariumData
import com.project.aqualife.data.TemperatureRegulatorSchedule

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDB = FirebaseDatabase.getInstance()

    private val _userLiveData = MutableLiveData<FirebaseUser?>()
    val userLiveData get() = _userLiveData
    private val _aquariumData = MutableLiveData<ArrayList<AquariumData>>()
    val aquariumData get() = _aquariumData

    private val aquariumDataArray = ArrayList<AquariumData>()

    // 로그인
    fun getUserData(id : String, pw : String){
        firebaseAuth.signInWithEmailAndPassword(id, pw).addOnCompleteListener {
            if(it.isSuccessful)
                _userLiveData.postValue(firebaseAuth.currentUser)
            else
                _userLiveData.postValue(firebaseAuth.currentUser)
        }
    }

    // 회원가입
    fun register(id : String, pw : String) {
        firebaseAuth.createUserWithEmailAndPassword(id, pw).addOnCompleteListener {
            if(it.isSuccessful)
                _userLiveData.postValue(firebaseAuth.currentUser)
        }
    }

    // 장치 On/Off 상태 변경
    fun changeState(name : String, part : String, state : String){
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}")
        reference.child(name).child(part).child("state").setValue(state)
    }

    // 스케쥴 추가하기s
    fun addSchedule(aquariumName: String, scheduleName: String, part: String, start : String, end : String, state: String){
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}").child(aquariumName).child(part).child("reservation").child(scheduleName)
        reference.child("start").setValue(start)
        reference.child("end").setValue(end)
        reference.child("state").setValue(state)
    }

    // 스케쥴 On/Off
    fun scheduleControl(aquariumName : String, scheduleName : String, part : String, state : String){
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}")
        reference.child(aquariumName).child(part).child("reservation").child(scheduleName).child("state").setValue(state)
    }

    // 온도 고정값 변경
    fun changeHolding(name : String, value : Int){
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}")
        reference.child(name).child("temperature").child("holdi" +
                "ng").setValue(value)
    }

    // Ph Max. Min 변경
    fun changePhSetting(name : String, Maxvalue : Float, Minvalue: Float){
        val a = String.format("%.1f",Maxvalue)
        val b = String.format("%.1f",Minvalue)
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}").child(name).child("ph")
        reference.child("warning_max").setValue(a)
        reference.child("warning_min").setValue(b)
    }

    fun changeFiltSetting(name: String, dayCode: String, starttime : String ,amount: Int) {
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}").child(name).child("filtration")
        reference.child("dayCode").setValue(dayCode)
        reference.child("starttime").setValue(starttime)
        reference.child("amount").setValue(amount)
    }

    // 어항 데이터 가져오기
    fun getAquariumData(){
        val reference = firebaseDB.reference.child("${firebaseAuth.uid}")
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                aquariumDataArray.clear()
                Log.d("DEB", "CALLED")
                for(v in snapshot.children){
                    val name = v.child("name").value.toString()
                    val date = v.child("date").value.toString()
                    val filtrationRemain = v.child("filtration").child("remain").value.toString()
                    val filtrationState = v.child("filtration").child("state").value.toString()

                    val lightReservation = ArrayList<TemperatureRegulatorSchedule>()
                    v.child("light").child("reservation").children.forEach {
                        val rName = it.key.toString()
                        val start = it.child("start").value.toString()
                        val end = it.child("end").value.toString()
                        val state = it.child("state").value.toString()
                        lightReservation.add(TemperatureRegulatorSchedule(name, start, end, rName, state, "light"))
                    }
                    val lightState = v.child("light").child("state").value.toString()

                    val co2Reservation = ArrayList<TemperatureRegulatorSchedule>()
                    v.child("co2").child("reservation").children.forEach {
                        val rName = it.key.toString()
                        val start = it.child("start").value.toString()
                        val end = it.child("end").value.toString()
                        val state = it.child("state").value.toString()
                        co2Reservation.add(TemperatureRegulatorSchedule(name, start, end, rName, state, "co2"))
                    }
                    val co2State = v.child("co2").child("state").value.toString()

                    val phWarningMin = v.child("ph").child("warning_min").value.toString()
                    val phWarningMax = v.child("ph").child("warning_max").value.toString()
                    val phValue = v.child("ph").child("value").value.toString()

                    val filtDayCode = v.child("filtration").child("dayCode").value.toString()
                    val filtAmount = v.child("filtration").child("amount").value.toString()
                    val filtStartTime = v.child("filtration").child("starttime").value.toString()
                    val filtRemain = v.child("filtration").child("remain").value.toString()


                    val temperatureHolding = v.child("temperature").child("holding").value.toString()
                    val temperatureValue = v.child("temperature").child("value").value.toString()

                    aquariumDataArray.add(AquariumData(name, date, filtrationRemain, filtrationState, lightReservation, lightState, co2State, co2Reservation, phWarningMin, phWarningMax, phValue, temperatureHolding, temperatureValue, filtDayCode,filtAmount,filtStartTime, filtRemain))
                }
                _aquariumData.postValue(aquariumDataArray)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun logout(){
        firebaseAuth.signOut()
    }
}