package com.example.bookmanagementapp.components.models

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object SignUpOrLoginScreen: Screen("SignUpOrLoginScreen")
    object AccountScreen: Screen("AccountScreen")
    object ContactsScreen: Screen("ContactsScreen")
    object TripsScreen: Screen("TripsScreen")
    object LoginScreen: Screen("LoginScreen")
    object SignUpScreen: Screen("SignUpScreen")
}