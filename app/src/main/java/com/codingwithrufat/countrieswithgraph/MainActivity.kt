package com.codingwithrufat.countrieswithgraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingwithrufat.countrieswithgraph.fragments.CountriesFragment
import com.codingwithrufat.countrieswithgraph.fragments.CountryDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_countries, CountriesFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_country_detail, CountryDetailFragment()).commit()

    }
}