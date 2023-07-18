package com.userslist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.userslist.models.UserLogin
import com.userslist.models.UserRequestData
import com.userslist.reposiory.UserLoginRepository

class UserLoginViewModel : ViewModel() {
    private val repository = UserLoginRepository(this)
    private val userLoginData: MutableLiveData<UserLogin?> = MutableLiveData()

    fun userLogin(userRequestData: UserRequestData) {
        repository.userLogin(userRequestData)
    }

    fun getUserData(): MutableLiveData<UserLogin?> {
        return this.userLoginData
    }

    fun setUserData(addressBook: UserLogin?) {
        this.userLoginData.value = addressBook
    }

}