import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.Alignment
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mad_14118.ui.theme.DarkBlue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mad_14118.api.GuitarPost
import androidx.compose.ui.unit.sp
import com.example.mad_14118.api.GuitarEdit


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditFormScreen(
    guitarId: Int,
    guitarTitle: String,
    guitarDescription: String,
    guitarPhone: String,
    guitarUrl: String,
    guitarColor: String,
    guitarSize: String,
    guitarPrice: String,
    guitarType: String
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(TextFieldValue(guitarTitle)) }
    var description by remember { mutableStateOf(TextFieldValue(guitarDescription)) }
    var phone by remember { mutableStateOf(TextFieldValue(guitarPhone)) }
    var urlImage by remember { mutableStateOf(TextFieldValue(guitarUrl)) }
    var color by remember { mutableStateOf(TextFieldValue(guitarColor)) }
    var size by remember { mutableStateOf(TextFieldValue(guitarSize)) }
    var price by remember { mutableStateOf(TextFieldValue(guitarPrice)) }
    var type by remember { mutableStateOf(TextFieldValue(guitarType)) }

    val inputs = listOf(title, description, phone, urlImage, color, size, price, type)

    Scaffold(
        content = {
            Column(
                modifier = Modifier.padding(16.dp),
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
                    onValueChange = {price = it
//                        newPrice ->
//                        if (newPrice.text.all { it.isDigit() }) {
//                            price = newPrice
//                        }
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
                         Button(
                                modifier = Modifier
                                    .padding(bottom = 25.dp)
                                    .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkBlue
                        ),
                        onClick = {
                            val inputStrings = inputs.map{ it.text }
                            if (validateInputs(inputStrings)) {
                                GuitarEdit(
                                    guitarId,
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
                            Text("Edit Guitar" , fontSize = 20.sp, )
                     }
                }
            }
        }
    )
}

private fun validateInputs(inputs: List<String>): Boolean {
    return inputs.all { it.isNotBlank() }
}
