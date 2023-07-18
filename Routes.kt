package com.userslist

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp : Routes("Signup")
    object Contacts : Routes("Contacts")
}