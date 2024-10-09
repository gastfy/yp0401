package com.example.demo.errors

import com.google.gson.Gson
import java.util.*

class ErrorDetails(private val timestamp: Date, private val message: String, private val details: String) {
    fun toJSON(): String {
        return Gson().toJson(this)
    }
}