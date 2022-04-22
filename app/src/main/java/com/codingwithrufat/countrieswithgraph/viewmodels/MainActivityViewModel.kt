package com.codingwithrufat.countrieswithgraph.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithrufat.countrieswithgraph.db.builder.DatabaseBuilder
import com.codingwithrufat.countrieswithgraph.db.entities.CountriesEntity
import com.codingwithrufat.countrieswithgraph.db.entities.LanguagesEntity
import com.codingwithrufat.countrieswithgraph.helper.Constants.TAG
import com.codingwithrufat.countrieswithgraph.helper.convertCountriesEntityToCountries
import com.codingwithrufat.countrieswithgraph.helper.convertCountriesToCountriesEntity
import com.codingwithrufat.countrieswithgraph.repositories.MainActivityRepository
import com.codingwithrufat.graphql.CountriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(private val mainActivityRepository: MainActivityRepository, private val context: Context): ViewModel(){

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
                    if (DatabaseBuilder.getCountryDatabase(context).getDao().getAllCountries().isEmpty()){ // because our api list is static
                        var list: MutableList<CountriesEntity> = mutableListOf()
                        list.addAll(convertCountriesToCountriesEntity(mainActivityRepository.getAllCountriesData()?.countries))
                        DatabaseBuilder.getCountryDatabase(context).getDao().insertDatabase(list)
                    }
                }else{
                    /**
                     * if data is failed(network or api problem)
                     * and db is not empty then get all data from catch
                     */
                    if (DatabaseBuilder.getCountryDatabase(context).getDao().getAllCountries().isNotEmpty()){
                        it.postValue(convertCountriesEntityToCountries(DatabaseBuilder.getCountryDatabase(context).getDao().getAllCountries()))
                    }
                }
            }

        }
    }

    fun setSelectedItem(item: CountriesQuery.Country){

        selectedMutableItem.value = item

    }

}