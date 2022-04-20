package com.codingwithrufat.countrieswithgraph.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithrufat.countrieswithgraph.R
import com.codingwithrufat.countrieswithgraph.adapters.RecyclerCountryAdapter
import com.codingwithrufat.countrieswithgraph.helper.convertListToString
import com.codingwithrufat.countrieswithgraph.listener.ItemClickListener
import com.codingwithrufat.countrieswithgraph.viewmodels.MainActivityViewModel
import com.codingwithrufat.graphql.CountriesQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : Fragment(), ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerCountryAdapter: RecyclerCountryAdapter
    private val viewModel: MainActivityViewModel by activityViewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_countries, container, false)

        getWidgets(view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerCountryAdapter = RecyclerCountryAdapter(requireContext(), this)
        recyclerView.adapter = recyclerCountryAdapter

        viewModel.response.observe(this, { countrylist ->
            recyclerCountryAdapter.updateCountryList(countrylist)
        })

        return view
    }

    private fun getWidgets(view: View) {
        recyclerView = view.findViewById(R.id.recyclerCountries)
    }

    override fun onClickItem(country: CountriesQuery.Country) {
        viewModel.setSelectedItem(country)
    }

}