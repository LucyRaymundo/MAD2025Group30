package com.example.safetyjourneyapplication.components.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao

@Composable
fun AccountScreen(
    userId: Int,
    navController: NavController,
    userDao: UserDao,
    contactDao: ContactDao
    ) {

    // call userId to get user info

}