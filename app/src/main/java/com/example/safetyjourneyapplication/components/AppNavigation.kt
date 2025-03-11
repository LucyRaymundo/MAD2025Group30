package com.example.safetyjourneyapplication.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookmanagementapp.components.models.Screen
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.example.safetyjourneyapplication.components.screens.MainScreen
import com.example.safetyjourneyapplication.components.screens.AccountScreen
import com.example.safetyjourneyapplication.components.screens.TripsScreen
import com.example.safetyjourneyapplication.components.screens.ContactsScreen
import com.example.safetyjourneyapplication.components.screens.LoginScreen
import com.example.safetyjourneyapplication.components.screens.SignUpOrLoginScreen
import com.example.safetyjourneyapplication.components.screens.SignUpScreen


@Composable
fun AppNavigation(
    userDao: UserDao,
    activityDao: ActivityDao,
    contactDao: ContactDao
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignUpOrLoginScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController, activityDao, userDao, contactDao)
        }
        composable(Screen.SignUpOrLoginScreen.route) {
            SignUpOrLoginScreen(navController)
        }
        composable(Screen.TripsScreen.route) {
            TripsScreen(navController, activityDao)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(navController, userDao, contactDao)
        }
        composable(Screen.ContactsScreen.route) {
            ContactsScreen(navController, userDao, contactDao)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController, userDao)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController, userDao)
        }
    }
}