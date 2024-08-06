package com.example.mad_14118.api

import android.content.Context
import android.content.Intent
import com.example.mad_14118.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

fun DeleteById(id: Int, context: Context) = runBlocking {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://wiutmadcw.uz/api/v1/records/$id?student_id=00014118")
        .delete()
        .build()

    withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        if (response.code == 200) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
        else {
            println(response.code)
        }
        response.body?.string()?.let { println(it) }
    }
}