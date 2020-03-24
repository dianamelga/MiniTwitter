package com.dianascode.minitwitter.app.retrofit

import com.dianascode.minitwitter.app.retrofit.request.LoginRequest
import com.dianascode.minitwitter.app.retrofit.request.SignUpRequest
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import com.dianascode.minitwitter.app.retrofit.response.Tweet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Diana Melgarejo on 3/18/2020.
 */
interface MiniTwitterService {

    @POST("auth/login")
    fun login(
        @Body request: LoginRequest): Call<AuthResponse>

    @POST("auth/signup")
    fun doSignUp(
        @Body request: SignUpRequest): Call<AuthResponse>

    @GET("tweets/all")
    fun getAllTweets(): Call<List<Tweet>>
}
