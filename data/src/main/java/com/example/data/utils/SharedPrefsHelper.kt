package com.example.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.data.constants.Constants

class SharedPrefsHelper(context: Context) {
    private val getSharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun saveAccessToken(token: String) {
        getSharedPreferences
            .edit()
            .putString(Constants.ACCESS_TOKEN, token)
            .apply()
    }
    fun getAccessToken() : String?{
        return getSharedPreferences.getString(Constants.ACCESS_TOKEN,null)
    }

}