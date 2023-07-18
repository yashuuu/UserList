package com.userslist.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.userslist.CustomTopAppBar
import com.userslist.models.User
import com.userslist.storeManager.StoreUserData
import com.userslist.viewModels.EmployeeViewModel
import com.userslist.viewModels.UserLoginViewModel
import kotlinx.coroutines.flow.Flow
import java.time.format.TextStyle

@Composable
fun ContactList(modifier: Modifier = Modifier, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarList(navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBarList(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Contacts", false)
        }, content = {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current
                val viewModel: EmployeeViewModel = viewModel()
                Spacer(modifier = Modifier.height(40.dp))
                UserInfoList(userList = viewModel.user, context)
            }
        })
}

@Composable
fun UserInfoList(userList: Flow<PagingData<User>>, context: Context) {
    val userListItems: LazyPagingItems<User> = userList.collectAsLazyPagingItems()

    LazyColumn {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, item.first_name, Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }

                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }

                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}