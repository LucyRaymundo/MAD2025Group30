package com.example.safetyjourneyapplication.components.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.example.safetyjourneyapplication.components.classes.Status
import com.example.safetyjourneyapplication.components.classes.User
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

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

    var contactUsername by remember { mutableStateOf("") }

    var newActivity by remember { mutableStateOf<Activity?>(null) }
    var activityId by remember { mutableIntStateOf(0) }

    var leaveTimeDate = LocalDateTime.now()

    val arriveTimeDate = LocalDateTime.now().plusHours(1)

    val startLocationString = "Muswell Hill, London, N10"

    var endLocation by remember { mutableStateOf("") }
    var journeyStatus by remember { mutableStateOf(Status.PENDING.id) }

    var showMenuItems by remember { mutableStateOf(false) }
    var showStatusUpdateAlert by remember { mutableStateOf(false) }

    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(51.5906, -0.1412), 12f)
    }

    var endLocationLatLng by remember { mutableStateOf<LatLng?>(null) }


    LaunchedEffect(userId) {
        coroutineScope.launch {
            userFirstName = userDao.getUserFirstName(userId)
            userLastName = userDao.getUserLastName(userId)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Map(
            cameraPositionState = cameraPositionState,
            endLocationLatLng = endLocationLatLng
        )

        IconButton(
            onClick = { showMenuItems = !showMenuItems },
            modifier = Modifier
                .padding(top = 70.dp, start = 350.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu, contentDescription = "nav menu",
                modifier = Modifier
                    .size(40.dp)
            )
        }

        if (showMenuItems == true) {

            MenuItems(
                navController = navController,
                contactDao = contactDao,
                userId = userId
            )

        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {

        Button(
            onClick = {
                journeyStatus = if (journeyStatus == Status.STARTED.id) {
                    Status.PAUSED.id
                } else {
                    Status.STARTED.id
                }

                coroutineScope.launch {

                    contactUsername = contactDao.getEmergencyContact(userId = userId) ?: ""
                    showStatusUpdateAlert = true
                    }

                    //activityDao.updateStatus(journeyStatus, activityId)
                },
            modifier = Modifier
                .padding(top = 50.dp, start = 15.dp)
        ) {
            Text("Status: $journeyStatus")
        }

        if (showStatusUpdateAlert) {
            StatusChangeAlertToContact(
                onClose = { showStatusUpdateAlert = false },
                contactUsername = contactUsername
            )
        }

        Text(
            "Welcome ${userFirstName} ${userLastName}, start your next journey !",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 40.dp)
        )



        TextField(
            value = startLocationString,
            onValueChange = {},
            label = { Text("Enter your start location") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 430.dp, bottom = 3.dp)
        )

        TextField(
            value = endLocation.replaceFirstChar { it.uppercase() } ,
            onValueChange = { endLocation = it},
            label = { Text("Enter your destination (Area, City, Postcode") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        )

        val context = LocalContext.current

        Button(
            onClick = {

                journeyStatus = Status.STARTED.id

                coroutineScope.launch {
                        if (endLocation.isNotBlank()) {

                            endLocationLatLng = convertEndLocationFromStringToLatLng(
                                endLocation = endLocation,
                                context = context
                            )

                            endLocationLatLng?.let {
                                cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 14f)
                            }
                        }
                    newActivity = Activity(
                            activityUserID = userId,
                            activityStartLocationName = startLocationString,
                            activityLeaveTimeDate = leaveTimeDate,
                            activityDestinationName = endLocation,
                            activityArriveTimeDate = arriveTimeDate,
                            activityStatusName = journeyStatus)
                            activityDao.insertActivity(newActivity!!)

                    activityId = activityDao.getActivityId(userId)
                }
            },
            modifier = Modifier.padding(top = 20.dp, start = 120.dp)
        ) {
            Text("Start journey")
        }
    }
}

@Composable
fun Map(
    cameraPositionState: CameraPositionState,
    endLocationLatLng: LatLng?
) {
    // Remember the marker state to prevent recomposition issues
    val markerState =
        remember { MarkerState(position = endLocationLatLng ?: LatLng(51.5906, -0.1412)) }

    // Remember the polyline points list so that it doesn't get cleared on recomposition
    val polylinePoints = remember { mutableStateListOf<LatLng>() }

    // Add starting and ending points to the polyline
    LaunchedEffect(endLocationLatLng) {
        polylinePoints.clear() // Clear previous points
        polylinePoints.add(LatLng(51.5906, -0.1412)) // Start point (Muswell Hill)
        if (endLocationLatLng != null) {
            polylinePoints.add(endLocationLatLng)
        } // Add the end point
    }
    Log.d("Map", "Polyline points: $polylinePoints") // Debug log to check points


    // Define the map UI and set the marker and polyline
    GoogleMap(
        modifier = Modifier
            .width(450.dp)
            .height(600.dp)
            .padding(top = 270.dp),
        cameraPositionState = cameraPositionState
    ) {
        // Place the destination marker
        Marker(
            state = markerState
        )

        // Draw the polyline
        Polyline(
            points = polylinePoints,
            color = Color.Blue,
            width = 10f
        )
    }
}



/* @Suppress("DEPRECATION")
suspend fun convertEndLocationFromStringToLatLng(context: Context, endLocation: String): LatLng? {
    return withContext(Dispatchers.IO) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addressList: List<Address>? = geocoder.getFromLocationName(endLocation, 1)
            addressList?.firstOrNull()?.let { address ->
                LatLng(address.latitude, address.longitude)
            }
        } catch (e: Exception) {
            null
        }
    }
} */

@Suppress("DEPRECATION")
fun convertEndLocationFromStringToLatLng(
    endLocation: String,
    context: Context
): LatLng? {

    val geocoder = Geocoder(context, Locale.getDefault())
    val addressList: List<Address>? = geocoder.getFromLocationName(endLocation, 1)

    if (addressList != null) {
        val address = addressList[0]
        return LatLng(address.latitude, address.longitude)
    }
    return null
}

@Composable
fun StatusChangeAlertToContact (
    onClose: () -> Unit,
    contactUsername: String

) {

    AlertDialog(
        onDismissRequest = { onClose() },
        text = { Text("$contactUsername has been alerted of your status update") },
        confirmButton = {
            Button(onClick = {
                onClose()
            })
            {
                Text("Confirm update alert")
            }
        }
    )

}


    @Composable
    fun MenuItems(
        navController: NavController,
        contactDao: ContactDao,
        userId: Int
    ) {

        val coroutineScope = rememberCoroutineScope()
        var showEmergencyAlert by remember { mutableStateOf(false) }
        var contactUsername by remember { mutableStateOf("") }


        Row {

            IconButton(
                onClick = { navController.navigate("Account_screen/${userId}") },
                modifier = Modifier
                    .padding(top = 60.dp, start = 150.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "account button",
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            IconButton(
                onClick = { navController.navigate("Trips_screen/${userId}") },
                modifier = Modifier
                    .padding(top = 60.dp, start = 15.dp)
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
                        contactUsername = contactDao.getEmergencyContact(userId = userId) ?: ""
                        showEmergencyAlert = true
                    }
                },
                modifier = Modifier
                    .padding(top = 60.dp, start = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "emergency alert button",
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            if (showEmergencyAlert == true) {

                EmergencyAlert(
                    contactUsername = contactUsername,
                    onClose = { showEmergencyAlert = false },

                    )
            }

            // add iconbutton for abandon trip = reset values back to original and notify that trip has been adbanoded = update status

        }
    }

    @Composable
    fun EmergencyAlert(
        contactUsername: String,
        onClose: () -> Unit,
    ) {

        AlertDialog(
            onDismissRequest = { onClose() },
            text = { Text("An emergency alert will be sent to $contactUsername") },
            confirmButton = {
                Button(onClick = {
                    onClose()
                })
                {
                    Text("Confirm Emergency Alert")
                }
            }
        )

    }


