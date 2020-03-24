package com.dianascode.minitwitter.app.common

import android.app.Application
import android.content.Context
import com.dianascode.minitwitter.app.retrofit.response.AuthResponse
import com.google.gson.Gson

/**
 * Created by Diana Melgarejo on 3/20/2020.
 */
class MiniTwitter : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private lateinit var instance: MiniTwitter
        private var authResponse: AuthResponse?=null

        fun getInstance(): MiniTwitter {
            return instance
        }

        fun getContext(): Context {
            return instance
        }

        fun getAuthResponse(): AuthResponse {
            return if (authResponse != null) {
                authResponse!!
            }else{
                Gson().fromJson(SharedPreferencesManager.getString(Constantes.PREF_AUTH_RESPONSE), AuthResponse::class.java)
            }

        }
    }
}