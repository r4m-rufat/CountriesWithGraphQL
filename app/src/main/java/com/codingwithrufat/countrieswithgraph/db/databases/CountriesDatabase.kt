package com.codingwithrufat.countrieswithgraph.db.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codingwithrufat.countrieswithgraph.db.converters.TypeConverter
import com.codingwithrufat.countrieswithgraph.db.daos.CountryDao
import com.codingwithrufat.countrieswithgraph.db.entities.CountriesEntity

@Database(entities = [CountriesEntity::class], version = 2, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class CountriesDatabase: RoomDatabase(){
    abstract fun getDao(): CountryDao
}