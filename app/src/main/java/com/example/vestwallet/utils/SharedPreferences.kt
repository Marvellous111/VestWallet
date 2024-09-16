package com.example.vestwallet.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.vestwallet.models.did.DidClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveBearerDidClass(bearerDidClass: DidClass) {
        val json = gson.toJson(bearerDidClass)
        prefs.edit().putString("bearer_did_class", json).apply()
    }

    fun getBearerDidClass(): DidClass? {
        val json = prefs.getString("bearer_did_class", null) ?: return null
        val type = object : TypeToken<DidClass>() {}.type
        return gson.fromJson(json, type)
    }
}