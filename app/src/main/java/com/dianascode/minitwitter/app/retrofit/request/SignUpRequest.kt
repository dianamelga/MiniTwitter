package com.dianascode.minitwitter.app.retrofit.request

import java.io.Serializable

/**
 * Created by Diana Melgarejo on 3/20/2020.
 */
data class SignUpRequest(
    var username: String?=null,
    var email: String?=null,
    var password: String?=null,
    var code: String?=null
): Serializable