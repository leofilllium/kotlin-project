package com.example.mad_14118

import FormScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mad_14118.ui.theme.MAD_14118Theme

class AddFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MAD_14118Theme {
                FormScreen()
            }
        }
    }
}