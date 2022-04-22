package com.codingwithrufat.countrieswithgraph.db.builder

import android.content.Context
import androidx.room.Room
import com.codingwithrufat.countrieswithgraph.db.databases.CountriesDatabase

/**
 * Singleton Database Builder class
 */
object DatabaseBuilder {

    private var INSTANCE: CountriesDatabase? = null

    fun getCountryDatabase(context: Context): CountriesDatabase {
        if (INSTANCE == null){
            synchronized(CountriesDatabase::class){
                INSTANCE = Room.databaseBuilder(
                    context,
                    CountriesDatabase::class.java,
                    "COUNTRIES_DB"
                ).build()
            }
        }
        return INSTANCE!!
    }

}