package com.dianascode.minitwitter.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dianascode.minitwitter.R
import com.dianascode.minitwitter.app.retrofit.MiniTwitterClient
import com.dianascode.minitwitter.app.retrofit.request.LoginRequest
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var miniTwitterClient: MiniTwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        miniTwitterClient = MiniTwitterClient.getInstance()

        btLogin.setOnClickListener {
            doLogin()
        }

        tvGoSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun doLogin(){
        val request = LoginRequest(etMail.text.toString(), etPassword.text.toString())
        val call = miniTwitterClient.miniTwitterService.login(request)

        call.enqueue(object: retrofit2.Callback<AuthResponse> {

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@MainActivity, DashboardActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    Toast.makeText(this@MainActivity, "Ha ocurrido un problema, verifique sus datos.", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Problemas de conexion", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
