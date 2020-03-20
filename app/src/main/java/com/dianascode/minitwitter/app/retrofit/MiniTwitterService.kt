package com.dianascode.minitwitter.app.retrofit

import com.dianascode.minitwitter.app.retrofit.request.LoginRequest
import com.dianascode.minitwitter.app.retrofit.request.SignUpRequest
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Diana Melgarejo on 3/18/2020.
 */
interface MiniTwitterService {

    @POST("auth/login")
    fun login(
        @Body request: LoginRequest): Call<AuthResponse>

    @POST("auth/login")
    fun doSignUp(
        @Body request: SignUpRequest): Call<AuthResponse>
}