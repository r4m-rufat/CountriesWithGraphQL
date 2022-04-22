package com.codingwithrufat.countrieswithgraph.db.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.codingwithrufat.graphql.CountriesQuery

@Entity(tableName = "COUNTRIES_TABLE")
class CountriesEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var native_name: String? = null,
    var phone: String? = null,
    var capital: String? = null,
    var emoji: String? = null,
    var currency: String? = null,
    var languages: List<LanguagesEntity>? = null
)