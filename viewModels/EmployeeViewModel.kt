package com.userslist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.userslist.models.User
import com.userslist.pagination.UserSource
import kotlinx.coroutines.flow.Flow

class EmployeeViewModel : ViewModel() {
    val user: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 6)) {
        UserSource()
    }.flow.cachedIn(viewModelScope)
}