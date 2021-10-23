package com.example.risingproject.src.main.home.homemain.util

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.risingproject.R
import com.example.risingproject.src.main.home.HomeFragment
import com.example.risingproject.src.main.home.homemain.models.pageIconInfo
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.StoreHomeFragemt
import com.example.risingproject.src.main.store.storehome.models.temp

class HomePagesGridViewAdapter(private var context: Context, val items: List<pageIconInfo>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(p0: Int): Any = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = items[p0]
        val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.gridview_item_homemain_pages, p2, false)

        val img = view.findViewById<ImageView>(R.id.gridview_page_img)
        val name = view.findViewById<TextView>(R.id.gridview_page_name)
        val gridview_message = view.findViewById<TextView>(R.id.gridview_message)

        img.setImageResource(item.pic)
        name.text = item.name
        if(item.code == 0){
            gridview_message.visibility = View.GONE
        }
        else{
            if(item.code == 1){
                gridview_message.text = "NEW"
            }
        }

        return view
    }
}