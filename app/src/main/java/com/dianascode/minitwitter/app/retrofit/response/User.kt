package com.dianascode.minitwitter.app.retrofit.response

/**
 * Created by Diana Melgarejo on 3/23/2020.
 */
data class User(
    var id: Int?=null,
    var username: String?=null,
    var descripcion: String?=null,
    var website: String?=null,
    var photoUrl: String?=null,
    var created: String?=null
)