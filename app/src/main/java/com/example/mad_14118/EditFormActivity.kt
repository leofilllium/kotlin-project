package com.example.mad_14118

import EditFormScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mad_14118.ui.theme.MAD_14118Theme

class EditFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val guitarId = intent.getIntExtra("guitarId", 0)
        val guitarTitle = intent.getStringExtra("guitarTitle")
        val guitarDescription = intent.getStringExtra("guitarDescription")
        val guitarPhone = intent.getStringExtra("guitarPhone")
        val guitarUrl = intent.getStringExtra("guitarUrl")
        val guitarColor = intent.getStringExtra("guitarColor")
        val guitarSize = intent.getStringExtra("guitarSize")
        val guitarPrice = intent.getStringExtra("guitarPrice")
        val guitarType = intent.getStringExtra("guitarType")
        setContent {
            MAD_14118Theme {
                EditFormScreen(
                    guitarId,
                    guitarTitle ?: "",
                    guitarDescription ?: "",
                    guitarPhone ?: "",
                    guitarUrl ?: "",
                    guitarColor ?: "",
                    guitarSize ?: "",
                    guitarPrice ?: "",
                    guitarType ?: ""
                )
            }
        }
    }
}
