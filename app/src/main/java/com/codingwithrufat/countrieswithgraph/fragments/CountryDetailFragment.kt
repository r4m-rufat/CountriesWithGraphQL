package com.codingwithrufat.countrieswithgraph.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.codingwithrufat.countrieswithgraph.R
import com.codingwithrufat.countrieswithgraph.helper.convertListToString
import com.codingwithrufat.countrieswithgraph.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_country_detail.view.*

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_country_detail, container, false)

        viewModel.selectedItem.observe(this, { item ->

            view.txt_detail_country_name.text = item.name
            view.txt_native.text = item.native
            view.txt_capital.text = item.capital
            view.txt_currency.text = item.currency
            view.txt_language_name.text = item.languages.convertListToString()
            view.txt_phone.text = item.phone

        })

        return view
    }

}