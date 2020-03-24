package com.dianascode.minitwitter.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dianascode.minitwitter.R
import com.dianascode.minitwitter.app.ui.adapters.MyTweetRecyclerViewAdapter
import com.dianascode.minitwitter.app.data.TweetViewModel
import com.dianascode.minitwitter.app.retrofit.response.Tweet

class TweetListFragment : Fragment() {
    private lateinit var myAdapter: MyTweetRecyclerViewAdapter
    private val tweetList = ArrayList<Tweet>()
    private lateinit var viewModel: TweetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TweetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)
        viewModel.getTweets().observe(viewLifecycleOwner,
            Observer<List<Tweet>> {
                tweetList.clear()
                tweetList.addAll(it)
                myAdapter.notifyDataSetChanged()
            })

        myAdapter =
            MyTweetRecyclerViewAdapter(
                tweetList
            )
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = myAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TweetListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
