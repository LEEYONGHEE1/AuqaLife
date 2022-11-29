package com.project.aqualife

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.aqualife.databinding.RegisterActivityBinding
import com.project.aqualife.viewModel.AuthViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : RegisterActivityBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.email
        val pw = binding.pw
        val pwCheck = binding.pwCheck
        val phoneNumber = binding.phoneNumber
        val register = binding.register

        register.setOnClickListener {
            if(id.text.isEmpty()){
                Toast.makeText(this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show()
                id.requestFocus()
            }
            else if(pw.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                pw.requestFocus()
            }
            else if(pw.text.length < 6){
                Toast.makeText(this, "비밀번호는 6자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
                pw.requestFocus()
            }
            else if(pw.text.toString() != pwCheck.text.toString()){
                Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                pw.requestFocus()
            }else if(phoneNumber.text.isEmpty()){
                Toast.makeText(this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                phoneNumber.requestFocus()
            }else
                authViewModel.register(id.text.toString().trim(), pw.text.toString().trim())
        }

        authViewModel.userLiveData.observe(this){
            if (it?.uid?.isNotEmpty() == true){
                authViewModel.logout()
                finish()
            } else{
                Toast.makeText(this, "회원가입에 실패했습니다. 입력 값을 확인하세요.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}