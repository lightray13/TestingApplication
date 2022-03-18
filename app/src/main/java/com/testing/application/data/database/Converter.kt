package com.testing.application.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun listToString(list: List<String>): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun stringToList(data: String): List<String> {
            val listType: Type = object: TypeToken<List<kotlin.String?>?>(){}.type
            return Gson().fromJson(data, listType)
        }
    }
}