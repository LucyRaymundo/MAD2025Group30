package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
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
import androidx.navigation.NavController
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Status
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.ContactDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

@Composable
fun AddTripScreen(
    userId: Int,
    navController: NavController,
    activityDao: ActivityDao,
    userDao: UserDao
) {
    val coroutineScope = rememberCoroutineScope()

    var userFirstName by remember { mutableStateOf("") }

    var newActivity by remember { mutableStateOf<Activity?>(null) }
    var activityName by remember { mutableStateOf("") }
    var activityDescription by remember { mutableStateOf("") }
    var activityLeaveTimeDate by remember { mutableStateOf<LocalDateTime?>(null) }
    var activityArriveTimeDate by remember { mutableStateOf<LocalDateTime?>(null) }

    var startLocation by remember { mutableStateOf("") }
    var endLocation by remember { mutableStateOf("") }

    var journeyStatus by remember { mutableStateOf("") }

    var showMenuItems by remember { mutableStateOf(false) }

    var journeyName by remember { mutableStateOf("") }
    var journeyDescription by remember { mutableStateOf("") }


    LaunchedEffect(userId) {
        coroutineScope.launch {
            userFirstName = userDao.getUserFirstName(userId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        IconButton(
            onClick = { showMenuItems = !showMenuItems },
            modifier = Modifier
                .padding(top = 60.dp, start = 350.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu, contentDescription = "nav menu",
                modifier = Modifier
                    .size(70.dp)
            )
        }

        if (showMenuItems == true) {

            MenuItems(
                navController = navController,
                userId = userId
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                "${userFirstName}, add a future journey !",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 110.dp)
            )

            TextField(
                value = startLocation,
                onValueChange = { startLocation = it },
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

            TextField(
                value = journeyName,
                onValueChange = { journeyName = it },
                label = { Text("Enter your destination") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            )

            TextField(
                value = journeyDescription,
                onValueChange = { journeyDescription = it },
                label = { Text("Enter your destination") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            )


            Button(
                onClick = {
                    journeyStatus = Status.PENDING.id
                    coroutineScope.launch {
                        /*newActivity = Activity(
                            activityName = activityName,
                            activityUserID = userId,
                            activityDescription = activityDescription,
                            activityStartLocationName = startLocation,
                            activityLeaveTimeDate =,
                            activityDestinationName = endLocation,
                            activityArriveTimeDate =,
                            activityStatusName = journeyStatus
                        )
                        activityDao.insertActivity(newActivity!!) */
                    }
                },
                modifier = Modifier.padding(top = 5.dp, start = 120.dp)
            ) {
                Text("Start journey")
            }
        }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun datePicker () {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var selectedDate by remember {
        mutableStateOf(
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(calendar.time)
        )
    }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    DatePicker(state = datePickerState)

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { millis ->
            val newCalendar = Calendar.getInstance().apply { timeInMillis = millis }
            selectedDate =
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(newCalendar.time)


        }


    }
}


@Composable
fun MenuItems(
    navController: NavController,
    userId: Int
) {

    Row {

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


    }
}