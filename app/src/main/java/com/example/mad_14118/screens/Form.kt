import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.ui.Alignment
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mad_14118.ui.theme.DarkBlue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mad_14118.api.GuitarPost
import androidx.compose.ui.unit.sp
import com.example.mad_14118.MainActivity
import com.example.mad_14118.api.DraftRecord


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormScreen() {
    val context = LocalContext.current
    var title by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var urlImage by remember { mutableStateOf(TextFieldValue()) }
    var color by remember { mutableStateOf(TextFieldValue()) }
    var size by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf(TextFieldValue()) }
    var type by remember { mutableStateOf(TextFieldValue()) }

    val inputs = listOf(title, description, phone, urlImage, color, size, price, type)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "Create Recod",
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
                }
            )
        },

        content = {

            Column(
                modifier = Modifier
                    .padding(top = 70.dp),

                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title" , fontSize = 18.sp ) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description" , fontSize = 18.sp) },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number" , fontSize = 18.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = urlImage,
                    onValueChange = { urlImage = it },
                    label = { Text("Image URL" , fontSize = 18.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color" , fontSize = 18.sp ) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = size,
                    onValueChange = { newSize ->
                        if (newSize.text.all { it.isDigit() }) {
                            size = newSize
                        }
                    },
                    label = { Text("Size", fontSize = 18.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = price,
                    onValueChange = { newPrice ->
                        if (newPrice.text.all { it.isDigit() }) {
                            price = newPrice
                        }
                    },
                    label = { Text("Price", fontSize = 18.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Type" , fontSize = 18.sp ) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                             Button(
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                        .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkBlue
                            ),
                            onClick = {
                                val inputStrings = inputs.map{ it.text }
                                if (validateInputs(inputStrings)) {
                                    GuitarPost(
                                        title.text,
                                        description.text,
                                        phone.text,
                                        urlImage.text, color.text,
                                        size.text,
                                        price.text,
                                        type.text,
                                        context
                                    )
                                } else {
                                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                                }
                            } ,
                            ){
                                Text("Add Guitar" , fontSize = 17.7.sp, color = Color.White)
                         }

                        Button(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            ),
                            onClick = {
                                val inputStrings = inputs.map{ it.text }
                                if (validateInputs(inputStrings)) {
                                    DraftRecord(
                                        title.text,
                                        description.text,
                                        phone.text,
                                        urlImage.text, color.text,
                                        size.text,
                                        price.text,
                                        type.text,
                                        context
                                    )


                                } else {
                                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                                }
                            } ,
                        ){
                            Text("Save as a Draft" , fontSize = 17.7.sp, textAlign = TextAlign.Center, color = Color.White)
                        }
                    }

                }

            }
        }
    )
}

private fun validateInputs(inputs: List<String>): Boolean {
    return inputs.all { it.isNotBlank() }
}
