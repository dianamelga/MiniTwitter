package com.dianascode.minitwitter.app.retrofit

import retrofit2.Call
import retrofit2.Response

/**
 * Created by Diana Melgarejo on 3/23/2020.
 */
abstract class MiniTwitterCallback<T>: retrofit2.Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        TODO("Not yet implemented")
    }
}