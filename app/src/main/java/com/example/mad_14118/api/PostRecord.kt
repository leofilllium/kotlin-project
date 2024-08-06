package com.example.mad_14118.api

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.mad_14118.MainActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONObject

fun GuitarPost(
    title: String,
    description: String,
    phone: String,
    urlImage: String,
    color: String,
    size: String,
    price: String,
    type: String,

    context: Context
){
    val requestBody = JSONObject()
    requestBody.put("title", title)
    requestBody.put("description", description)
    requestBody.put("phone", phone)
    requestBody.put("url", urlImage)
    requestBody.put("color", color)
    requestBody.put("size", size)
    requestBody.put("price", price)
    requestBody.put("type", type)

    val request = Request.Builder()
        .url("https://wiutmadcw.uz/api/v1/records?student_id=00014118")
        .post(requestBody.toString().toRequestBody("application/json".toMediaType()))
        .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        override fun onResponse(call: Call, response: Response) {
            if (response.code == 201) {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                println(response.code)

            } else {
                println(response.code)

            }
        }
    })
}