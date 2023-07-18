package com.userslist.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.userslist.CustomTopAppBar
import com.userslist.Routes
import com.userslist.models.UserRequestData
import com.userslist.storeManager.StoreUserData
import com.userslist.viewModels.UserRegisterViewModel

@Composable
fun SignUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(navController: NavHostController) {
    val viewModel: UserRegisterViewModel = viewModel()
    val data = viewModel.getUser().observeAsState()
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Signup", true)
        },
        content =
        {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                val mContext = LocalContext.current
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val dataStore = StoreUserData(mContext)
                val scope = rememberCoroutineScope()

                Text(
                    text = "Register",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 40.sp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(60.dp, 0.dp, 60.dp, 0.dp)) {
                    Button(
                        onClick = {
                            if (username.value.text.isNotEmpty() and password.value.text.isNotEmpty()) {
                                val userDataRequest =
                                    UserRequestData(username.value.text, password.value.text)
                                viewModel.userRegister(userDataRequest)
                                if (data.value != null) {
                                val email = data.value?.token
                               /* scope.launch {
                                    if (email != null) {
                                        dataStore.saveEmail(email)
                                    }
                                }*/
                                navController.navigate(Routes.Contacts.route)
                            }
                            } else {
                                Toast.makeText(
                                    mContext,
                                    "Please enter correct Username & Password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Signup")
                    }
                }
            }
        })
}