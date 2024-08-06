package com.example.mad_14118

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_14118.api.fetchDraftRecords
import com.example.mad_14118.api.fetchRecords
import com.example.mad_14118.ui.theme.DarkBlue
import com.example.mad_14118.ui.theme.MAD_14118Theme
import com.example.mad_14118.data.Record
import com.example.mad_14118.screens.HomeContent
import com.example.mad_14118.screens.ProfileContent
import com.example.mad_14118.screens.Screens
import com.example.mad_14118.widgets.NavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val records = remember { mutableStateOf(emptyList<Record>()) }
            val draftrecords = remember { mutableStateOf(emptyList<Record>()) }
            LaunchedEffect(Unit) {
                records.value = fetchRecords()
                draftrecords.value = fetchDraftRecords()
            }

            MAD_14118Theme {
                val context = LocalContext.current
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { NavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.HomeScreen.screen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screens.HomeScreen.screen) { HomeContent(context, records.value) }
                        composable(Screens.Profile.screen) { ProfileContent(context,draftrecords.value) }
                    }
                }
            }
        }
    }
}














