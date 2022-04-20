package com.codingwithrufat.countrieswithgraph.listener

import com.codingwithrufat.graphql.CountriesQuery

interface ItemClickListener {
    fun onClickItem(country: CountriesQuery.Country)
}