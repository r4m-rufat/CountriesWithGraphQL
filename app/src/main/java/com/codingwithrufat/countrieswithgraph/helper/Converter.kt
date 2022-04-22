package com.codingwithrufat.countrieswithgraph.helper

import android.util.Log
import com.codingwithrufat.countrieswithgraph.db.entities.CountriesEntity
import com.codingwithrufat.countrieswithgraph.db.entities.LanguagesEntity
import com.codingwithrufat.countrieswithgraph.helper.Constants.TAG
import com.codingwithrufat.graphql.CountriesQuery

/**
 * convert the name of languages to String
 */
fun List<CountriesQuery.Language>.convertListToString(): String {
        var string = ""
        this.forEachIndexed { index, item ->
            string += if ((this.size - 1) == index){
                "${item.name}"
            }else{
                "${item.name}, "
            }
        }
        return string
    }

/**
 * Country list which comes from api converts to list of CountriesEntity class for database
 */
fun convertCountriesToCountriesEntity(countriesList: List<CountriesQuery.Country>?): List<CountriesEntity>{
    var list: MutableList<CountriesEntity> = mutableListOf()
    countriesList?.forEach { country ->
        var languageList: MutableList<LanguagesEntity> = mutableListOf()
        country.languages.forEach {
            languageList.add(LanguagesEntity(it.code, it.name, it.native))
        }
        var countriesEntity = CountriesEntity()
        countriesEntity.name = country.name
        countriesEntity.native_name = country.native
        countriesEntity.capital = country.capital
        countriesEntity.phone = country.phone
        countriesEntity.emoji = country.emoji
        countriesEntity.currency = country.currency
        countriesEntity.languages = languageList
        list.add(countriesEntity)
    }
    return list
}


/**
 * convert list of CountriesEntity to list of CountriesQuery for recycler adapter
 */
fun convertCountriesEntityToCountries(countriesList: List<CountriesEntity>): List<CountriesQuery.Country>{
    var list: MutableList<CountriesQuery.Country> = mutableListOf()
    countriesList?.forEach { country ->
        var languageList: MutableList<CountriesQuery.Language> = mutableListOf()
        country.languages?.forEach {
            languageList.add(CountriesQuery.Language(it.code+"", it.name, it.native))
        }
        Log.d(TAG, "convertCountriesEntityToCountries: ${country.native_name}")
        list.add(CountriesQuery.Country(country.name+"", country.native_name+"", country.phone+"", country.capital+"", country.emoji+"", country.currency+"", languageList))
    }
    return list
}

