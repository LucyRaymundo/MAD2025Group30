package com.example.bookmanagementapp.components.models

sealed class Screen(val route: String) {
    object MainScreen : Screen("Main_screen/{userId}")
    object SignUpOrLoginScreen: Screen("SignUpOrLoginScreen")
    object AccountScreen: Screen("Account_screen/{userId}")
    object ContactsScreen: Screen("Contacts_screen/{userId}")
    object TripsScreen: Screen("Trips_screen/{userId}")
    object LoginScreen: Screen("LoginScreen")
    object SignUpScreen: Screen("SignUpScreen")
}