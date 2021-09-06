package com.example.data.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun Long.toDateTime(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val netDate = Date(this)
    return sdf.format(netDate)
}