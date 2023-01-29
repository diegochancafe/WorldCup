package com.diegochancafe.worldcup.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun listToJson(types: List<String?>?): String {
        return Gson().toJson(types)
    }

    @TypeConverter
    fun jsonToList(types: String?): List<String> {
        return Gson().fromJson<List<String>>(
            types,
            object : TypeToken<ArrayList<String?>?>() {}.type
        )
    }
}