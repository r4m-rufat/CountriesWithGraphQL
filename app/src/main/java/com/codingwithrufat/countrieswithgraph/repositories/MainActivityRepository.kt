package com.codingwithrufat.countrieswithgraph.repositories

import com.apollographql.apollo3.ApolloClient
import com.codingwithrufat.graphql.CountriesQuery
import javax.inject.Inject

class MainActivityRepository
@Inject constructor(private val apolloClient: ApolloClient){

    suspend fun getAllCountriesData() = apolloClient.query(CountriesQuery()).execute().data

}