package com.dianascode.minitwitter.app.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dianascode.minitwitter.R
import com.dianascode.minitwitter.app.common.Constantes.Companion.PREF_AUTH_RESPONSE
import com.dianascode.minitwitter.app.common.SharedPreferencesManager
import com.dianascode.minitwitter.app.retrofit.MiniTwitterClient
import com.dianascode.minitwitter.app.retrofit.request.LoginRequest
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import com.google.gson.Gson
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
            finish()
        }
    }

    private fun doLogin(){
        val request = LoginRequest(etMail.text.toString(), etPassword.text.toString())
        val call = miniTwitterClient.miniTwitterService.login(request)

        call.enqueue(object: retrofit2.Callback<AuthResponse> {

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show()
                    //TODO test
                    SharedPreferencesManager.saveString(PREF_AUTH_RESPONSE, Gson().toJson(response.body()))

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
