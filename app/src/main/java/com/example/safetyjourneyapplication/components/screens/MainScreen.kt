package com.example.safetyjourneyapplication.components.screens

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.bookmanagementapp.components.models.Screen
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Location
import com.example.safetyjourneyapplication.components.classes.User
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun MainScreen(
    userId: Int,
    navController: NavController,
    activityDao: ActivityDao,
    userDao: UserDao,
    contactDao: ContactDao
) {
    val coroutineScope = rememberCoroutineScope()

    var userFirstName by remember { mutableStateOf("") }
    var userLastName by remember { mutableStateOf("") }

    var newActivity by remember { mutableStateOf<Activity?>(null) }
    var activityName by remember { mutableStateOf("") }
    var activityDescription by remember { mutableStateOf("") }
    var activityLeaveTimeDate by remember {  mutableStateOf<LocalDateTime?>(null) }
    var activityArriveTimeDate by remember {  mutableStateOf<LocalDateTime?>(null) }

    var startLocation by remember { mutableStateOf("") }
    var endLocation by remember { mutableStateOf("") }

    var showMenuItems by remember { mutableStateOf(false) }


    LaunchedEffect(userId) {
        coroutineScope.launch {
            userFirstName = userDao.getUserFirstName(userId)
            userLastName = userDao.getUserLastName(userId)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Map()

        IconButton(
            onClick = { showMenuItems = !showMenuItems },
            modifier = Modifier
                .padding(top = 60.dp,start = 350.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu, contentDescription = "nav menu",
                modifier = Modifier
                    .size(70.dp)
            )
        }

        if (showMenuItems == true) {

            menuItems(
                navController = navController,
                contactDao = contactDao,
                userId = userId
            )

        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Welcome ${userFirstName} ${userLastName}, start your next journey !",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 110.dp)
        )
        

        TextField(
            value = startLocation,
            onValueChange = { startLocation = it  },
            label = { Text("Enter your start location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 450.dp, bottom = 3.dp)
        )

        TextField(
            value = endLocation,
            onValueChange = { endLocation = it },
            label = { Text("Enter your destination") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                   //newActivity = Activity(activityName = activityName, activityUserID = userId, activityDescription = activityDescription, activityStartLocationID = , activityStartLocationName = startLocation, activityLeaveTimeDate = , activityDestinationID = , activityDestinationName = endLocation, activityStatusID = , activityStatusName =  )
                   //activityDao.insertActivity(newActivity)
                }
            },
            modifier = Modifier.padding(top = 5.dp, start = 120.dp)
        ) {
            Text("Start journey")
        }
    }
    }

}


@Composable
fun Map() {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(51.5074, -0.1278), 12f) // Default to London
    }
        GoogleMap(
            modifier = Modifier
                .width(450.dp)
                .height(600.dp)
                .padding(top = 240.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = LatLng(51.5074, -0.1278)), // Example marker
                title = "London",
                snippet = "Welcome to London!"
            )
        }
}


@Composable
fun menuItems(
    navController: NavController,
    contactDao: ContactDao,
    userId: Int
) {

    val coroutineScope = rememberCoroutineScope()
    var contactUsername by remember { mutableStateOf("") }
    var showEmergencyAlert by remember {mutableStateOf(false) }

    Row{

        IconButton(
            onClick = { navController.navigate("Account_screen/${userId}") },
            modifier = Modifier
                .padding(top = 60.dp, start = 150.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle, contentDescription = "account button",
                modifier = Modifier
                    .size(40.dp)
            )
        }

        IconButton(
            onClick = {navController.navigate("Trips_screen/${userId}")},
            modifier = Modifier
                .padding(top = 60.dp, start = 15.dp )
        ) {
            Icon(
                imageVector = Icons.Default.Place, contentDescription = "trips button",
                modifier = Modifier
                    .size(40.dp)
            )
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    contactUsername = contactDao.getEmergencyContact(userId = userId)
                    showEmergencyAlert = true
                }
            },
            modifier = Modifier
                .padding(top = 60.dp, start = 15.dp )
        ) {
            Icon(
                imageVector = Icons.Default.Warning, contentDescription = "emergency alert button",
                modifier = Modifier
                    .size(40.dp)
            )
        }

        if (contactUsername != "" && showEmergencyAlert == true) {

            emergencyAlert(
                contactUsername = contactUsername,
                onClose = {showEmergencyAlert = false},

            )
        }

    }
}

@Composable
fun emergencyAlert(
    contactUsername: String,
    onClose: () -> Unit,
){

    AlertDialog(
        onDismissRequest = {onClose},
        text = { Text("An emergency alert will be sent to $contactUsername") },
        confirmButton = {
            Button(onClick = {onClose
            })
            {
                Text("Confirm Emergency Alert")
            }
        }
    )

}


