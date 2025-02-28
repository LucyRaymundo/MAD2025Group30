package com.example.bookmanagementapp.components.models

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object SignUpScreen: Screen("SignUpScreen")
    object AccountScreen: Screen("AccountScreen")
    object ContactsScreen: Screen("ContactsScreen")
    object TripsScreen: Screen("TripsScreen")

}