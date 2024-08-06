package com.example.mad_14118.data
import org.json.JSONObject

data class Record(
    val id: Int,
    val studentId: String,
    val title: String,
    val description: String,
    val phone: String,
    val url: String,
    val color: String,
    val size: String,
    val price: String,
    val type: String,
    val createdAt: String,
    val updatedAt: String
)
