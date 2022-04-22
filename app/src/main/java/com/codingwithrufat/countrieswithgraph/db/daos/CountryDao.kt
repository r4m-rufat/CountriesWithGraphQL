package com.codingwithrufat.countrieswithgraph.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codingwithrufat.countrieswithgraph.db.entities.CountriesEntity
import com.codingwithrufat.graphql.CountriesQuery

@Dao
interface CountryDao {

    @Query("SELECT * FROM COUNTRIES_TABLE")
    suspend fun getAllCountries(): List<CountriesEntity>

    @Insert
    suspend fun  insertDatabase(countryList: List<CountriesEntity>)

}