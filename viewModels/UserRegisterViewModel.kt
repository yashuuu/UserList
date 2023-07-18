package com.userslist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.userslist.models.UserRegister
import com.userslist.models.UserRequestData
import com.userslist.reposiory.UserRegisterRepository

class UserRegisterViewModel : ViewModel() {
    private val repository = UserRegisterRepository(this)
    private val userRegisterData: MutableLiveData<UserRegister?> = MutableLiveData()

    fun userRegister(userRequestData: UserRequestData) {
        repository.userRegister(userRequestData)
    }

    fun getUser(): MutableLiveData<UserRegister?> {
        return this.userRegisterData
    }

    fun setUser(userRegister: UserRegister?) {
        this.userRegisterData.value = userRegister
    }

}