package com.codingwithrufat.countrieswithgraph.db.converters

import androidx.room.TypeConverter
import com.codingwithrufat.countrieswithgraph.db.entities.LanguagesEntity
import com.google.gson.Gson

class TypeConverter {

    @TypeConverter
    fun listToJson(value: List<LanguagesEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<LanguagesEntity>::class.java).toList()
}