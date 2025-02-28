package com.example.safetyjourneyapplication.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookmanagementapp.components.models.Screen
import com.example.safetyjourneyapplication.components.screens.MainScreen
import com.example.safetyjourneyapplication.components.screens.AccountScreen
import com.example.safetyjourneyapplication.components.screens.TripsScreen
import com.example.safetyjourneyapplication.components.screens.ContactsScreen
import com.example.safetyjourneyapplication.components.screens.SignUpScreen



@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screen.TripsScreen.route) {
            TripsScreen(navController)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(navController)
        }
        composable(Screen.ContactsScreen.route) {
            ContactsScreen(navController)
        }
    }
}