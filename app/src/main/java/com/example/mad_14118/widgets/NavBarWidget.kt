package com.example.mad_14118.widgets

import android.content.Intent
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_14118.AddFormActivity
import com.example.mad_14118.screens.Screens
import com.example.mad_14118.ui.theme.DarkBlue
import com.example.mad_14118.ui.theme.GrayInactive



@Composable
fun NavBar(navController: NavController) {
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val context = LocalContext.current

    BottomAppBar(containerColor = DarkBlue) {
        IconButton(
            onClick = {
                selected.value = Icons.Default.Home
                navController.navigate(Screens.HomeScreen.screen) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Home) Color.White else GrayInactive
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)

                .padding(16.dp),
            contentAlignment = Alignment.Center,

        ) {

            FloatingActionButton(

                onClick = {
                    val intent = Intent(context, AddFormActivity::class.java)
                    context.startActivity(intent)
                },
                containerColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = DarkBlue,
                    modifier = Modifier


                )
            }
        }

        IconButton(
            onClick = {
                selected.value = Icons.Default.AccountCircle
                navController.navigate(Screens.Profile.screen) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.AccountCircle) Color.White else GrayInactive
            )
        }
    }
}




