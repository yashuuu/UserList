package com.userslist.models

import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("token") var token : String? = null
)
