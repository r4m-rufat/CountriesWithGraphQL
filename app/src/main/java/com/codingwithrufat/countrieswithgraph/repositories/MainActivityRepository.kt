package com.codingwithrufat.countrieswithgraph.repositories

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.codingwithrufat.countrieswithgraph.helper.Constants.TAG
import com.codingwithrufat.graphql.CountriesQuery
import java.lang.Exception
import javax.inject.Inject

class MainActivityRepository
@Inject constructor(private val apolloClient: ApolloClient){

    suspend fun getAllCountriesData():CountriesQuery.Data? {
        try {
            return apolloClient.query(CountriesQuery()).execute().data
        }catch (e: Exception){
            Log.d(TAG, "getAllCountriesData: Request is failed")
        }
        return null
    }

}