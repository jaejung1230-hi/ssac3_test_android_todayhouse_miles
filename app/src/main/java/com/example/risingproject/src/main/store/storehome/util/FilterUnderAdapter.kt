package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.risingproject.R
import com.example.risingproject.src.main.store.storehome.models.InfoForUnderFilter


class FilterUnderAdapter(private var context: Context, val items: InfoForUnderFilter): BaseAdapter() {

    override fun getCount(): Int = items.filters.size

    override fun getItem(p0: Int): Any = items.filters[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        when(items.type){
            "color" -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_color, p2, false)

                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                name.text = item

                return view
            }
            "check" -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_check, p2, false)

                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                name.text = item

                return view
            }
            else -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_radio, p2, false)

                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                name.text = item

                return view
            }
        }
    }
}