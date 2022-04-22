package com.codingwithrufat.countrieswithgraph.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithrufat.countrieswithgraph.R
import com.codingwithrufat.countrieswithgraph.helper.Constants.TAG
import com.codingwithrufat.countrieswithgraph.listener.ItemClickListener
import com.codingwithrufat.graphql.CountriesQuery
import com.codingwithrufat.graphql.type.Country
import kotlin.math.log

class RecyclerCountryAdapter constructor(private val context: Context, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<RecyclerCountryAdapter.ViewHolder>() {

    private var list: List<CountriesQuery.Country> = listOf()

    fun updateCountryList(list: List<CountriesQuery.Country>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerCountryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_country_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerCountryAdapter.ViewHolder, position: Int) {
        holder._country_name.text = list[position].name
        holder._capital_name.text = list[position].capital

        holder.itemView.setOnClickListener {
            itemClickListener.onClickItem(list[position])
        }

    }

    override fun getItemCount(): Int {
        if (list.isEmpty()){
            return 0
        }
        return list.size
    }

    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val _country_name: TextView = itemview.findViewById(R.id.country_name)
        val _capital_name: TextView = itemview.findViewById(R.id.capital_name)
    }

}