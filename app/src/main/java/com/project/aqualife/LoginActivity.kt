package com.project.aqualife

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.aqualife.databinding.LoginActivityBinding
import com.project.aqualife.viewModel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : LoginActivityBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.userLiveData.observe(this){
            if(it?.uid != null){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else
                Toast.makeText(this, "아이디 또는 비밀번호를 잘못입력하셨습니다.", Toast.LENGTH_SHORT).show()
        }

        val id = binding.userId
        val pw = binding.userPw
        val login = binding.tryLogin
        val register = binding.register

        login.setOnClickListener {
            if(id.text.isEmpty()){
                id.requestFocus()
                Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pw.text.isEmpty()){
                pw.requestFocus()
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authViewModel.tryLogin(id.text.toString(), pw.text.toString())
        }

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}