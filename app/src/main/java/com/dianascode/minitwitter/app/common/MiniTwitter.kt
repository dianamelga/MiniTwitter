package com.dianascode.minitwitter.app.common

import android.app.Application
import android.content.Context

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
        fun getInstance(): MiniTwitter {
            return instance
        }

        fun getContext(): Context {
            return instance
        }
    }
}