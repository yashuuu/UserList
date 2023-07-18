package com.userslist.reposiory

import com.userslist.models.UserRegister
import com.userslist.models.UserRequestData
import com.userslist.retrofit.RetrofitInstance
import com.userslist.viewModels.UserRegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegisterRepository(val viewModel: UserRegisterViewModel) {
    fun userRegister(
        dataModel: UserRequestData
    ) {

        val retrofitAPI = RetrofitInstance.apiInterface
        val call: Call<UserRegister?>? = retrofitAPI.userRegister(dataModel)
        call!!.enqueue(object : Callback<UserRegister?> {
            override fun onResponse(call: Call<UserRegister?>, response: Response<UserRegister?>) {
                val model: UserRegister? = response.body()
                viewModel.setUser(model)
            }

            override fun onFailure(call: Call<UserRegister?>, t: Throwable) {
                viewModel.setUser(null)
            }
        })

    }

    /*   suspend fun userLogin(userRequestData: UserRequestData): UserLogin {
           return userLoginService.userLogin(userRequestData)
       }*/
}