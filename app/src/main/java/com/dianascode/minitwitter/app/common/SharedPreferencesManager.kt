package com.dianascode.minitwitter.app.common

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Diana Melgarejo on 3/23/2020.
 */
class SharedPreferencesManager {

    companion object {
        private const val APP_SETTINGS_FILE = "APP_SETTINGS"
        private val sharedPreferences: SharedPreferences by lazy {
            MiniTwitter.getContext()
                .getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun saveString(key: String, value: String) {
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun saveBoolean(key: String, value: Boolean) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getString(key: String): String? {
            return sharedPreferences.getString(key, null)
        }

        fun getBoolean(key: String): Boolean {
            return sharedPreferences.getBoolean(key, false)
        }

    }
}