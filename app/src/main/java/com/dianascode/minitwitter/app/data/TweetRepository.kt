package com.dianascode.minitwitter.app.data
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dianascode.minitwitter.app.common.MiniTwitter
import com.dianascode.minitwitter.app.retrofit.MiniTwitterClient
import com.dianascode.minitwitter.app.retrofit.response.Tweet
import retrofit2.Call
import retrofit2.Response


/**
 * Created by Diana Melgarejo on 3/23/2020.
 */
class TweetRepository {
    private val client = MiniTwitterClient.getInstance()
    var tweets = getAllTweets()

    fun getAllTweets(): LiveData<List<Tweet>> {
        val data = MutableLiveData<List<Tweet>>()
        val call = MiniTwitterClient.getInstance().miniTwitterService.getAllTweets()
        call.enqueue(object: retrofit2.Callback<List<Tweet>> {
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(MiniTwitter.getContext(), "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if(response.isSuccessful) {
                    data.value = response.body()!!
                }else{
                    Toast.makeText(MiniTwitter.getContext(), "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

        })
        return data

    }


}