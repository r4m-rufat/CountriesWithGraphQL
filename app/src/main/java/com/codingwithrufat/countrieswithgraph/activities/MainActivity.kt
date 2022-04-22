package com.codingwithrufat.countrieswithgraph.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingwithrufat.countrieswithgraph.R
import com.codingwithrufat.countrieswithgraph.fragments.CountriesFragment
import com.codingwithrufat.countrieswithgraph.fragments.CountryDetailFragment
import com.codingwithrufat.countrieswithgraph.helper.checkLandscapeMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkLandscapeMode(this)){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_country_detail, CountryDetailFragment()).commit()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_countries, CountriesFragment()).commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragments, CountriesFragment()).commit()
        }

    }

}