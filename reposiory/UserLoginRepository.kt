package com.userslist.reposiory

import com.userslist.models.UserLogin
import com.userslist.models.UserRequestData
import com.userslist.retrofit.RetrofitInstance
import com.userslist.viewModels.UserLoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLoginRepository(val viewModel: UserLoginViewModel) {
    fun userLogin(
        dataModel: UserRequestData
    ) {

        val retrofitAPI = RetrofitInstance.apiInterface
        val call: Call<UserLogin?>? = retrofitAPI.userLogin(dataModel)
        call!!.enqueue(object : Callback<UserLogin?> {
            override fun onResponse(call: Call<UserLogin?>, response: Response<UserLogin?>) {
                val model: UserLogin? = response.body()
                viewModel.setUserData(model)
            }

            override fun onFailure(call: Call<UserLogin?>, t: Throwable) {
                viewModel.setUserData(null)
            }
        })

    }

    /*   suspend fun userLogin(userRequestData: UserRequestData): UserLogin {
           return userLoginService.userLogin(userRequestData)
       }*/
}