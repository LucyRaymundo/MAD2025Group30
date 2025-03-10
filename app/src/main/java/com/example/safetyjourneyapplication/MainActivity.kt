package com.example.safetyjourneyapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.safetyjourneyapplication.components.AppNavigation
import com.example.safetyjourneyapplication.components.daos.UserDao
import com.example.safetyjourneyapplication.ui.theme.SafetyJourneyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseInstance.getDatabase(applicationContext)
        val userDao = db.UserDao()
        val activityDao = db.ActivityDao()
        val contactDao = db.ContactDao()

        enableEdgeToEdge()
        setContent {
            SafetyJourneyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AppNavigation(
                            userDao = userDao,
                            activityDao = activityDao,
                            contactDao = contactDao
                        )
                    }
                }
            }
        }
    }