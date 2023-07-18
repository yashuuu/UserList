package com.userslist.retrofit

import com.userslist.constants.IConstants
import com.userslist.models.UserLogin
import com.userslist.models.UserRegister
import com.userslist.models.UserRequestData
import com.userslist.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST(IConstants.POST_USER_LOGIN)
    fun userLogin(@Body userRequestData: UserRequestData?
    ): Call<UserLogin?>?

    @POST(IConstants.POST_USER_REGISTER)
    fun userRegister(@Body userRequestData: UserRequestData?
    ): Call<UserRegister?>?

    @GET("api/users")
    suspend fun getUserList(@Query("page") page: Int) : UserResponse
}