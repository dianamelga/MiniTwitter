package com.dianascode.minitwitter.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dianascode.minitwitter.R
import com.dianascode.minitwitter.app.retrofit.MiniTwitterClient
import com.dianascode.minitwitter.app.retrofit.request.SignUpRequest
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import kotlinx.android.synthetic.main.activity_sing_up.*
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var miniTwitterClient: MiniTwitterClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        supportActionBar?.hide()

        miniTwitterClient = MiniTwitterClient.getInstance()

        btSignUp.setOnClickListener {
            doSignUp()
        }

        tvGoLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun doSignUp() {
        val request = SignUpRequest(etUsername.text.toString(),
            etMail.text.toString(),
            etPassword.text.toString(),
            "UDEMYANDROID"
        )
        val call = miniTwitterClient.miniTwitterService.doSignUp(request)
        call.enqueue(object: retrofit2.Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                //Toast.makeText()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                TODO("Not yet implemented")
            }

        })
    }
}
