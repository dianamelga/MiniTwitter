package com.dianascode.minitwitter.app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dianascode.minitwitter.app.retrofit.response.Tweet

/**
 * Created by Diana Melgarejo on 3/24/2020.
 */
class TweetViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TweetRepository()
    private val tweets: LiveData<List<Tweet>> = repository.getAllTweets()

    fun getTweets(): LiveData<List<Tweet>> {
        return tweets
    }




}