package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.Column
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
import kotlin.random.Random

@Composable
fun SignUpScreen(
    navController: NavController,
    userDao: UserDao
    ) {

    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var newUser by remember { mutableStateOf<User?>(null) }
    var userId by remember { mutableStateOf<Int?>(0) }
    var showConfirmAlert by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Sign Up to StaySafe",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 100.dp, bottom = 30.dp)
        )

        TextField(
            value = firstName.replaceFirstChar { it.uppercase() },
            onValueChange = { firstName = it },
            label = { Text("Enter your first name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        TextField(
            value = lastName.replaceFirstChar { it.uppercase() },
            onValueChange = { lastName = it },
            label = { Text("Enter your last name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        TextField(
            value = phoneNum,
            onValueChange = { phoneNum = it },
            label = { Text("Enter your phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Enter a username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter a password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    newUser = User(userFirstName = firstName, userLastName = lastName, userPhone = phoneNum, userName = userName, userPassword = password)
                    userDao.insertUser(newUser!!)
                    userId = userDao.getUserId(userName)
                }
                showConfirmAlert = true
            },
            modifier = Modifier.padding(top = 15.dp, start = 135.dp)
        ) {
            Text("Sign Up")
        }

        if (showConfirmAlert == true && newUser != null) {
                ConfirmUserAddedAlert(
                    newUser = newUser!!,
                    onClose = { showConfirmAlert = false },
                    onNavigate = { navController.navigate("Main_screen/${userId}")}
                )
        }

        Button(
            onClick = {
                navController.popBackStack() },
            modifier = Modifier
                .padding(start = 145.dp, top = 20.dp)
        )
        {
            Text( "Back")
        }
    }
}

@Composable
fun ConfirmUserAddedAlert(
    newUser: User,
    onClose: () -> Unit,
    onNavigate: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onClose,
        text = { Text("${newUser.userFirstName}, welcome to StaySafe !") },
        confirmButton = {
            Button(onClick = {
                onClose()
                onNavigate()
            })
            {
                Text("Get Started !")
            }
        }
    )
}