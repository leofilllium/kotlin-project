package com.example.mad_14118.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import com.example.mad_14118.data.Record
import okio.IOException

suspend fun fetchRecords(): List<Record> {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://wiutmadcw.uz/api/v1/records/all?student_id=00014118")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }

            val responseBody = response.body?.string() ?: ""
            val jsonObject = JSONObject(responseBody)
            val dataArray = jsonObject.getJSONArray("data")
            val records = mutableListOf<Record>()

            for (i in 0 until dataArray.length()) {
                val item = dataArray.getJSONObject(i)
                records.add(item.toRecord())
            }

            records
        }
    }
}

private fun JSONObject.toRecord(): Record {
    return Record(
        id = getInt("id"),
        studentId = getString("student_id"),
        title = getString("title"),
        description = getString("description"),
        phone = getString("phone"),
        url = getString("url"),
        color = getString("color"),
        size = getString("size"),
        price = getString("price"),
        type = getString("type"),
        createdAt = getString("created_at"),
        updatedAt = getString("updated_at")
    )
}
