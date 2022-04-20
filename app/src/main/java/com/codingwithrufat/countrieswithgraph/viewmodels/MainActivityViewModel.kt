package com.codingwithrufat.countrieswithgraph.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithrufat.countrieswithgraph.helper.Constants.TAG
import com.codingwithrufat.countrieswithgraph.repositories.MainActivityRepository
import com.codingwithrufat.graphql.CountriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(private val mainActivityRepository: MainActivityRepository): ViewModel(){

    private val countries_data = MutableLiveData<List<CountriesQuery.Country>>()
    private val selectedMutableItem = MutableLiveData<CountriesQuery.Country>()

    val response: LiveData<List<CountriesQuery.Country>>
    get() = countries_data

    val selectedItem: LiveData<CountriesQuery.Country>
    get() = selectedMutableItem

    init {
        getCountriesData()
    }

    private fun getCountriesData(){
        viewModelScope.launch {

            countries_data.let {
                if (mainActivityRepository.getAllCountriesData() != null){
                    it.postValue(mainActivityRepository.getAllCountriesData()?.countries)
                    Log.d(TAG, "getCountriesData: Countries list is full")
                }else{
                    Log.d(TAG, "getCountriesData: Countries list is null")
                }
            }

        }
    }

    public fun setSelectedItem(item: CountriesQuery.Country){

        selectedMutableItem.value = item

    }

}