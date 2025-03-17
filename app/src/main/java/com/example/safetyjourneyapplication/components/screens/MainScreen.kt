package com.example.safetyjourneyapplication.components.screens

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Location
import com.example.safetyjourneyapplication.components.classes.User
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
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

    LaunchedEffect(userId) {
        coroutineScope.launch {
            userFirstName = userDao.getUserFirstName(userId)
            userLastName = userDao.getUserLastName(userId)
        }
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
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )

        TextField(
            value = startLocation,
            onValueChange = { startLocation = it  },
            label = { Text("Enter your start location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 360.dp, bottom = 10.dp)
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
            modifier = Modifier.padding(top = 15.dp, start = 120.dp)
        ) {
            Text("Start journey")
        }
    }

}

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    AndroidView(
        factory = { mapView },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Restrict height to avoid blank screen
    ) { mapView ->
        mapView.onCreate(Bundle())  // Ensure lifecycle methods are handled
        mapView.onResume()

        mapView.getMapAsync { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(10f))
        }
    }
}