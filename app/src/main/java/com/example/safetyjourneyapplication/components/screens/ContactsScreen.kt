package com.example.safetyjourneyapplication.components.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao

@Composable
fun ContactsScreen(
    userId: Int,
    navController: NavController,
    userDao: UserDao,
    contactDao: ContactDao
) {
    // allow user to create contacts here and view them

    // display all contacts

    // allow user to set one contact as emergency contact so that when they press the emergency alert button in main screen = alert is sent to correct user

}