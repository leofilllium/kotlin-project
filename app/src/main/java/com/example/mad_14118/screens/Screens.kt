package com.example.mad_14118.screens

sealed class Screens(val screen: String) {
    object HomeScreen : Screens("HomeScreen")
    object Profile : Screens("profile")
}