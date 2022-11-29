package com.project.aqualife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.aqualife.adapter.ViewPagerAdapter
import com.project.aqualife.data.AquariumData
import com.project.aqualife.databinding.ActivityMainBinding
import com.project.aqualife.fragment.OnBackPressedListener
import com.project.aqualife.viewModel.DataViewModel
import com.project.aqualife.viewModel.AuthViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var tabName = listOf("PH", "HOME", "FILTRATION", "LIGHT", "REGULATOR", "TEMPERATURE")
    var viewPager : ViewPager2? = null
    private val authViewModel : AuthViewModel by viewModels()
    private val dataViewModel : DataViewModel by viewModels()
    private var backPressedListener : OnBackPressedListener? = null
    private var backPressedTime = 0L
    lateinit var aquariumSpinner : Spinner
    var aquariumList = ArrayList<AquariumData>()
    private val aquariumName = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        aquariumSpinner = binding.spinner

        val tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        viewPager!!.adapter = ViewPagerAdapter(this)
        viewPager!!.currentItem = 1

        // 어항 데이터가 갱신되었을 때 observer
        authViewModel.aquariumData.observe(this){
            aquariumList = it

            // 원래 name array 와 비교하여 변경을 감지
            var isChanged = false
            aquariumList.forEach{ v ->
                if(!aquariumName.contains(v.name)){
                    isChanged = true
                    aquariumName.add(v.name)
                }
            }

            // 만약 변경되었다면 adapter 를 갱신함
            if(isChanged){
                val adapter = ArrayAdapter(applicationContext, R.layout.aquarium_spinner, aquariumName)
                aquariumSpinner.adapter = adapter
            }
        }

        TabLayoutMediator(tabLayout, viewPager!!){ tab, pos ->
            tab.text = tabName[pos]
        }.attach()

        // ViewPager 순환 스크롤
        viewPager?.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            var currentPos = 0
            var currentState = 0

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                handleScroll(state)
                currentState = state
            }

            private fun handleScroll(state : Int){
                if(state == ViewPager2.SCROLL_STATE_IDLE && currentState == ViewPager2.SCROLL_STATE_DRAGGING){
                    if(currentState != ViewPager2.SCROLL_STATE_SETTLING){
                        val lastPosition = viewPager!!.adapter?.itemCount!!

                        if(currentPos == 0)
                            viewPager!!.currentItem = lastPosition
                        else
                            viewPager!!.currentItem = 0
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                super.onPageSelected(position)
            }
        })

        aquariumSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d("CA", "CHANGED")
                dataViewModel.updateIndexValue(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        // 초기 인덱스 지정
//        dataViewModel.updateIndexValue(0)
        authViewModel.getAquariumData()
    }

    fun getSpinnerData() : Pair<String, Int>{
        return Pair(aquariumSpinner.selectedItem.toString(), aquariumSpinner.selectedItemPosition)
    }

    // Fragment 에서 뒤로가기 버튼 클릭시 리스너 변경
    fun setOnBackPressedListener(listener: OnBackPressedListener?){
        this.backPressedListener = listener
    }

    // 현재 Fragment 에 따른 뒤로가기 버튼 동작 분기
    override fun onBackPressed() {
        if(backPressedListener != null)
            backPressedListener!!.onBackPressed()
        else{
            if(System.currentTimeMillis() > backPressedTime + 2000){
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(this, "한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            }else if(System.currentTimeMillis() <= backPressedTime + 2000)
                finish()
        }
    }

    // 앱 종료기 Firebase sign out
    override fun onDestroy() {
        super.onDestroy()
        authViewModel.logout()
    }
}