package com.example.risingproject.src.main.store.storehome.util

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
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.StoreHomeFragemt
import com.example.risingproject.src.main.store.storehome.models.temp

class StoreHomeCategoryGridViewAdapter(private var context: Context, val fragemt: StoreHomeFragemt, val items: List<temp>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(p0: Int): Any = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = items[p0]
        val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.gridview_item_storehome_category, p2, false)

        val img = view.findViewById<ImageView>(R.id.gridview_storehome_category_img)
        val name = view.findViewById<TextView>(R.id.gridview_storehome_category_name)
        val subcategory = view.findViewById<TextView>(R.id.gridview_storehome_category_subcategory)

        img.setImageResource(item.img)
        name.text = item.name
        subcategory.text = item.sub

        view.setOnClickListener {
            val storeCategoryFragment: Fragment = StoreCategoryFragment()
            val fragmentManager: FragmentManager? = fragemt.fragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            val bundle : Bundle = Bundle()
            bundle.putString("category", item.name)
            storeCategoryFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.main_frm, storeCategoryFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }
}