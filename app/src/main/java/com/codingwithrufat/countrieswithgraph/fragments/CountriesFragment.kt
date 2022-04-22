package com.codingwithrufat.countrieswithgraph.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithrufat.countrieswithgraph.R
import com.codingwithrufat.countrieswithgraph.adapters.RecyclerCountryAdapter
import com.codingwithrufat.countrieswithgraph.helper.checkLandscapeMode
import com.codingwithrufat.countrieswithgraph.helper.convertListToString
import com.codingwithrufat.countrieswithgraph.listener.ItemClickListener
import com.codingwithrufat.countrieswithgraph.viewmodels.MainActivityViewModel
import com.codingwithrufat.graphql.CountriesQuery
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_countries.view.*

@AndroidEntryPoint
class CountriesFragment : Fragment(), ItemClickListener {

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

        setupRecyclerView(view)

        viewModel.response.observe(this, { countrylist ->
            recyclerCountryAdapter.updateCountryList(countrylist)
        })

        return view
    }

    private fun setupRecyclerView(view: View){
        view.recyclerCountries.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        recyclerCountryAdapter = RecyclerCountryAdapter(requireContext(), this)
        view.recyclerCountries.adapter = recyclerCountryAdapter
    }

    override fun onClickItem(country: CountriesQuery.Country) {
        if (!checkLandscapeMode(requireContext())){
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragments, CountryDetailFragment(), "country_detail_fragment").addToBackStack("country_detail_fragment").commit()
        }
        viewModel.setSelectedItem(country)
    }

}