package com.dianascode.minitwitter.app.retrofit.response

import java.io.Serializable

/**
 * Created by Diana Melgarejo on 3/23/2020.
 */
data class Tweet(
    var id: Int?=null,
    var mensaje: String?=null,
    var likes: ArrayList<Like>?=null,
    var user: User?=null
): Serializable

