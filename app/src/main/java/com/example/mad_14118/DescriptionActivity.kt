package com.example.mad_14118

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mad_14118.api.DeleteById
import com.example.mad_14118.ui.theme.DarkBlue
import com.example.mad_14118.ui.theme.BlackTxt
import com.example.mad_14118.ui.theme.MAD_14118Theme
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




class DescriptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val guitarId = intent.getIntExtra("guitarId", 0)
        val guitarDescription = intent.getStringExtra("guitarDescription")
        val guitarTitle = intent.getStringExtra("guitarTitle")
        val guitarPhone = intent.getStringExtra("guitarPhone")
        val guitarUrl = intent.getStringExtra("guitarUrl")
        val guitarColor = intent.getStringExtra("guitarColor")
        val guitarSize = intent.getStringExtra("guitarSize")
        val guitarPrice = intent.getStringExtra("guitarPrice")
        val guitarType = intent.getStringExtra("guitarType")
        setContent {
            MAD_14118Theme {
                GuitarDetailsScreen(
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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GuitarDetailsScreen(
    guitarId: Int,
    guitarTitle: String,
    guitarDescription: String,
    guitarPhone: String,
    guitarUrl: String,
    guitarColor: String,
    guitarSize: String,
    guitarPrice: String,
    guitarType: String
)
{
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        guitarTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    var expanded by remember { mutableStateOf(false) }

                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(onClick = {
                            val intent = Intent(context, EditFormActivity::class.java)
                            intent.putExtra("guitarId", guitarId)
                            intent.putExtra("guitarTitle", guitarTitle)
                            intent.putExtra("guitarDescription", guitarDescription)
                            intent.putExtra("guitarPhone", guitarPhone)
                            intent.putExtra("guitarUrl", guitarUrl)
                            intent.putExtra("guitarColor", guitarColor)
                            intent.putExtra("guitarSize", guitarSize)
                            intent.putExtra("guitarPrice", guitarPrice)
                            intent.putExtra("guitarType", guitarType)
                            context.startActivity(intent)
                            expanded = false
                        },
                            text = {Text("Edit" , fontSize = 18.sp, color = BlackTxt ) }
                        )
                        DropdownMenuItem(onClick = {
                            DeleteById(
                                guitarId,
                                context
                            )
                            expanded = false
                        },
                            text = { Text("Delete", fontSize = 18.sp, color = BlackTxt)  }
                        )
                    }
                },
            )
        },
    )
    {
        Column(
            modifier = Modifier
                .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        {
            Image(
                painter = rememberAsyncImagePainter(guitarUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )

            Text(text = "Guitar: $guitarDescription", fontSize = 20.sp)
            Text(text = "Color: $guitarColor" , fontSize = 20.sp)
            Text(text = "Size: $guitarSize", fontSize = 20.sp)
            Text(text = "Price: $guitarPrice" , fontSize = 20.sp)
            CallButton(phoneNumber = guitarPhone)


        }
    }
}

@Composable
fun CallButton(phoneNumber: String) {
    val context = LocalContext.current
    val makeCall = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:$phoneNumber" ,)

            }
            makeCall.launch(intent)
        },
        modifier = Modifier
            .padding(bottom = 25.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkBlue
        ),
        content = {
            Text("Call $phoneNumber" , fontSize = 18.sp, color = Color.White)
        }
    )
}