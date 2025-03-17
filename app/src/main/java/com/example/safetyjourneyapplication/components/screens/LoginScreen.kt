package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmanagementapp.components.models.Screen
import com.example.safetyjourneyapplication.components.classes.User
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    userDao: UserDao
) {
    val coroutineScope = rememberCoroutineScope()

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var userId by remember { mutableStateOf<Int?>(null) }
    var userFirstName by remember { mutableStateOf("") }

    var showConfirmAlert by remember {mutableStateOf(false)}
    var showErrorAlert by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Login to StaySafe",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 100.dp, bottom = 30.dp)
        )

        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Enter your username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter your password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    userId = userDao.checkUserExists(userName, password)
                    if (userId != null) {
                        userFirstName = userDao.getUserFirstName(userId!!)
                        showConfirmAlert = true
                    }
                    else {
                        showErrorAlert = true
                    }
                }
            },
            modifier = Modifier.padding(top = 15.dp, start = 135.dp)
        ) {
            Text("Login")
        }

        if (showConfirmAlert == true && userId != null) {
            ConfirmUserExistsAlert(
                userFirstName = userFirstName,
                onClose = { showConfirmAlert = false },
                onNavigate = { navController.navigate("Main_screen/${userId}")}
            )
        }

        if (showErrorAlert == true && userId == null) {
            ConfirmUserDoesNotExistAlert(
                onClose = { showErrorAlert = false },
                onNavigateToSignUp = {  navController.navigate(Screen.SignUpScreen.route) }
            )
        }

        Button(
            onClick = {
                navController.popBackStack() },
            modifier = Modifier
                .padding(start = 135.dp, top = 20.dp)
        )
        {
            Text( "Back")
        }
    }
}


@Composable
fun ConfirmUserExistsAlert(
    userFirstName: String,
    onClose: () -> Unit,
    onNavigate: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onClose,
        text = { Text("${userFirstName}, welcome back to StaySafe !") },
        confirmButton = {
            Button(onClick = {
                onClose()
                onNavigate()
            })
            {
                Text("Enter")
            }
        }
    )
}


@Composable
fun ConfirmUserDoesNotExistAlert(
    onClose: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        text = { Text("Your username and/or password doesn't exist, try again or sign up") },
        confirmButton = {
            Button(onClick = {
                onClose()
            }) {
                Text("Try again")
            }
        },
        dismissButton = {
            Button(onClick = {
                onNavigateToSignUp()
            }) {
                Text("Sign Up")
            }
        }
    )
}
