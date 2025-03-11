package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao

@Composable
fun MainScreen(
    navController: NavController,
    activityDao: ActivityDao,
    userDao: UserDao,
    contactDao: ContactDao
) {
    Text(
        "Main Screen",
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(top = 100.dp, bottom = 30.dp)
    )

}