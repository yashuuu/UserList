package com.userslist.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.userslist.models.User
import com.userslist.retrofit.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class UserSource : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPage = params.key ?: 1
            val userList = RetrofitInstance.apiInterface.getUserList(page = nextPage)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}