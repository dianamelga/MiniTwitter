package com.dianascode.minitwitter.app.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dianascode.minitwitter.R
import com.dianascode.minitwitter.app.common.Constantes.Companion.API_MINITWITTER_PHOTOS_BASE_URL
import com.dianascode.minitwitter.app.common.MiniTwitter
import com.dianascode.minitwitter.app.retrofit.response.Tweet
import com.squareup.picasso.Picasso


class MyTweetRecyclerViewAdapter(
    private val mValues: List<Tweet>
) : RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tweet_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.username?.text = item.user?.username
        holder.message?.text = item.mensaje
        holder.likesCount?.text = "${item.likes?.size ?: 0}"
        if(item.user?.photoUrl != null) {
            Picasso.get().load(API_MINITWITTER_PHOTOS_BASE_URL +item.user?.photoUrl)
                .into(holder.avatar)
        }else {
            holder.avatar?.setImageDrawable(ContextCompat.getDrawable(MiniTwitter.getContext(), R.drawable.ic_logo_minituiter_mini))
        }

        if(item.likes?.filter { like -> like.username == MiniTwitter.getAuthResponse().username }?.size ?: 0 > 0) {
            holder.like?.setImageDrawable(ContextCompat.getDrawable(MiniTwitter.getContext(), R.drawable.ic_like_pink))
            holder.likesCount?.setTextColor(
                ContextCompat.getColor(
                    MiniTwitter.getContext(),
                    R.color.pink
                )
            )
        }else{
            holder.like?.setImageDrawable(ContextCompat.getDrawable(MiniTwitter.getContext(), R.drawable.ic_like))
            holder.likesCount?.setTextColor(
                ContextCompat.getColor(
                    MiniTwitter.getContext(),
                    R.color.gray
                )
            )
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var avatar: ImageView? = null
        var like: ImageView? = null
        var likesCount: TextView? = null
        var username: TextView? = null
        var message: TextView? = null

        init {
            avatar = mView.findViewById(R.id.ivAvatar)
            like = mView.findViewById(R.id.ivLike)
            likesCount = mView.findViewById(R.id.tvLikesCount)
            username = mView.findViewById(R.id.tvUsername)
            message = mView.findViewById(R.id.tvMessage)

        }
    }
}
