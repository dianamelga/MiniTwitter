package com.dianascode.minitwitter.app.retrofit.request

import java.io.Serializable

/**
 * Created by Diana Melgarejo on 3/18/2020.
 */
data class LoginRequest(
    var email: String?=null,
    var password: String?=null
): Serializable



