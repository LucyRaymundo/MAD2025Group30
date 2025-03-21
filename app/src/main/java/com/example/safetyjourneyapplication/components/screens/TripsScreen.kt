package com.example.safetyjourneyapplication.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
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
import com.example.safetyjourneyapplication.components.classes.Activity
import com.example.safetyjourneyapplication.components.classes.Status
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

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

                MenuItemsForTripScreen(
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

            MostRecentOrCurrentJourney(
                userId = userId,
                activityDao = activityDao
            )


            Text(
                "Past journeys",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            PastJourneys(
                userId = userId,
                activityDao = activityDao
            )


            Text(
                "Future journeys",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            FutureJourneys(
                userId = userId,
                activityDao = activityDao
            )


        }
    }


}

@Composable
fun MenuItemsForTripScreen(
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
            onClick = { navController.navigate("Main_screen/${userId}") },
            modifier = Modifier
                .padding(top = 60.dp, start = 15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Home, contentDescription = "main button",
                modifier = Modifier
                    .size(40.dp)
            )

            IconButton(
                onClick = { navController.navigate("AddTrip_screen/${userId}") },
                modifier = Modifier
                    .padding(top = 60.dp, start = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "add trip button",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }

    }
}

@Composable
fun MostRecentOrCurrentJourney(
    userId: Int,
    activityDao: ActivityDao
) {
        val coroutineScope = rememberCoroutineScope()
        var currentJourney by remember { mutableStateOf<Activity?>(null) }

        val paused = Status.PAUSED.id
        val started = Status.STARTED.id

        LaunchedEffect(userId) {
            coroutineScope.launch {
                currentJourney = activityDao.getMostRecentActivity(userId, started, paused)
            }
        }

    if(currentJourney != null) {
        Card {

            Text(
                "${currentJourney?.activityName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "${currentJourney?.activityStartLocationName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "${currentJourney?.activityDestinationName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "Status: ${currentJourney?.activityStatusName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "E.T.A: ${currentJourney?.activityArriveTimeDate}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

        }
    }else {
        Text(
            "You have no current journey",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
        )
    }


    }


@Composable
    fun PastJourneys(
        userId: Int,
        activityDao: ActivityDao
    ) {
    val coroutineScope = rememberCoroutineScope()
    var pastJourneys by remember { mutableStateOf<List<Activity>?>(null) }

    val completed = Status.COMPLETED.id
    val abandoned = Status.ABANDONED.id

    LaunchedEffect(userId) {
        coroutineScope.launch {
            pastJourneys = activityDao.getPastActivities(userId, completed, abandoned)
        }
    }

    LazyColumn {
        items(pastJourneys ?: emptyList()) { journey ->
            Text(
                journey.activityName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                journey.activityStartLocationName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                journey.activityDestinationName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "Status: ${journey.activityStatusName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "E.T.A: ${journey.activityArriveTimeDate}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            if (pastJourneys == null) {
                Text(
                    "You have no past journeys",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
                )

            }

        }
    }
}


@Composable
    fun FutureJourneys(
        userId: Int,
        activityDao: ActivityDao
    ) {
    val coroutineScope = rememberCoroutineScope()
    var currentTimeDate = LocalDateTime.now()
    var futureJourneys by remember { mutableStateOf<List<Activity>?>(null) }

    LaunchedEffect(userId) {
        coroutineScope.launch {
            futureJourneys = activityDao.getFutureActivities(userId, currentTimeDate)
        }
    }


    LazyColumn {
        items(futureJourneys ?: emptyList()) { journey ->
            Text(
                journey.activityName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                journey.activityStartLocationName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                journey.activityDestinationName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "Status: ${journey.activityStatusName}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            Text(
                "E.T.A: ${journey.activityArriveTimeDate}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
            )

            if (futureJourneys == null) {
                Text(
                    "You have no future journeys",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 130.dp, bottom = 30.dp)
                )

            }

        }
    }
}


