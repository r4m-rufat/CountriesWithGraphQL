package com.codingwithrufat.countrieswithgraph.listener

import com.codingwithrufat.graphql.CountriesQuery

// when clicked country item in adapter
interface ItemClickListener {
    fun onClickItem(country: CountriesQuery.Country)
}