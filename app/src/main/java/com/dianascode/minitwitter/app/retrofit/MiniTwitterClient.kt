package com.dianascode.minitwitter.app.retrofit

import com.dianascode.minitwitter.app.common.Constantes.Companion.API_MINITWITTER_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Diana Melgarejo on 3/19/2020.
 */
class MiniTwitterClient private constructor() {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_MINITWITTER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val miniTwitterService = retrofit.create(MiniTwitterService::class.java)


    companion object {
        private var mInstance: MiniTwitterClient?=null
        fun getInstance(): MiniTwitterClient {
            if(mInstance == null) {
                mInstance = MiniTwitterClient()
            }
            return mInstance as MiniTwitterClient
        }


    }
}