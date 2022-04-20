package com.codingwithrufat.countrieswithgraph.helper

import com.codingwithrufat.graphql.CountriesQuery

fun <T> List<T>.convertListToString(): String {
        var string = ""
        this.forEachIndexed { index, item ->
            string += if ((this.size - 1) == index){
                "$item"
            }else{
                "${item}, "
            }
        }
        return string
    }
