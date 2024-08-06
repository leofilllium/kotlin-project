package com.example.mad_14118.screens
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mad_14118.DescriptionActivity
import com.example.mad_14118.widgets.GuitarListItem
import com.example.mad_14118.data.Record
import com.example.mad_14118.ui.theme.DarkBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    context: Context,
    records: List<Record>
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkBlue,
                titleContentColor = Color.White,
            ),
            title = {
                Text(
                    "Drafts",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
    }
    )
    {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .padding(top = 50.dp)

        ) {

            items(
                items = records,
                itemContent = { guitar ->
                    GuitarListItem(
                        guitar = guitar,
                        onClick = {
                            val intent = Intent(context, DescriptionActivity::class.java)
                            intent.putExtra("guitarId", guitar.id)
                            intent.putExtra("guitarTitle", guitar.title)
                            intent.putExtra("guitarDescription", guitar.description)
                            intent.putExtra("guitarPhone", guitar.phone)
                            intent.putExtra("guitarUrl", guitar.url)
                            intent.putExtra("guitarColor", guitar.color)
                            intent.putExtra("guitarSize", guitar.size)
                            intent.putExtra("guitarPrice", guitar.price)
                            intent.putExtra("guitarType", guitar.type)
                            context.startActivity(intent)
                        }
                    )
                }
            )
        }
    }
}

