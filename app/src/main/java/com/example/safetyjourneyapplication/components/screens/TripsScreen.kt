package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.safetyjourneyapplication.components.daos.ActivityDao
import com.example.safetyjourneyapplication.components.daos.UserDao
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun TripsScreen(
    userId: Int,
    navController: NavController,
    activityDao: ActivityDao,
    userDao: UserDao
) {

    val coroutineScope = rememberCoroutineScope()
    var userFirstName by remember { mutableStateOf("") }

    var showMenuItems by remember { mutableStateOf(false) }

    LaunchedEffect(userId) {
        coroutineScope.launch {
            userFirstName = userDao.getUserFirstName(userId)
        }
    }

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

            menuItemsForTripScreen(
                navController = navController,
                userId = userId
            )

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Button(onClick = { /*TODO*/ }) {
            // add journey button

        }

        Text(
            "${userFirstName}'s journeys",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )

        Text(
            "Current Journey",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )

        Text(
            "Past journeys",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )

        //list

        Text(
            "Future journeys",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )

        //list
    }


}
@Composable
fun menuItemsForTripScreen(
    navController: NavController,
    userId: Int
) {
    Row {

        IconButton(
            onClick = { navController.navigate("Account_screen/${userId}") },
            modifier = Modifier
                .padding(top = 60.dp, start = 223.dp)
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

@Composable
fun mostRecentOrCurrentJourney(
    userId: Int,
    activityDao: ActivityDao
){
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(userId) {
        coroutineScope.launch {
            activityDao.getMostRecentActivity(userId)
        }
    }

    Card {

    }

}


@Composable
fun pastJourneys(
    userId: Int,
    activityDao: ActivityDao
){
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(userId) {
        coroutineScope.launch {
            activityDao.getActivities(userId)
        }
    }

    LazyColumn {

    }

}


@Composable
fun futureJourneys(
    userId: Int,
    activityDao: ActivityDao
){
    val coroutineScope = rememberCoroutineScope()
    var currentTimeDate = LocalDateTime.now()

    LaunchedEffect(userId) {
        coroutineScope.launch {
            activityDao.getFutureActivities(userId, currentTimeDate)
        }
    }

    LazyColumn {

    }

}


