package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmanagementapp.components.models.Screen
import com.example.safetyjourneyapplication.R
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao

@Composable
fun SignUpOrLoginScreen(navController: NavController, userDao: UserDao, contactDao: ContactDao) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Welcome to StaySafe",
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 200.dp, bottom = 30.dp, start = 20.dp)
        )

        Text(
            "Login or Sign up below",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 23.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 230.dp, start = 100.dp)
    ) {

        Button(
            onClick = { navController.navigate(Screen.LoginScreen.route) },
            modifier = Modifier.padding(top = 200.dp, start = 50.dp)
        ) {
            Text("Login")
        }

        Button(
            onClick = { navController.navigate(Screen.SignUpScreen.route)  },
            modifier = Modifier.padding(top = 15.dp, start = 45.dp)
        ) {
            Text("Sign Up")
        }

    }

}