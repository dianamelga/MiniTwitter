package com.dianascode.minitwitter.app.retrofit.response

import java.io.Serializable

/**
 * Created by Diana Melgarejo on 3/20/2020.
 */
data class AuthResponse(
    var token: String?=null,
    var username: String?=null,
    var email: String?=null,
    var photoUrl: String?=null,
    var created: String?=null,
    var active: Boolean?=null
): Serializable