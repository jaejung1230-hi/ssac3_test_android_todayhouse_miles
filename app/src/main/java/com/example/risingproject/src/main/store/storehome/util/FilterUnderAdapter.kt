package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.risingproject.R
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.models.InfoForUnderFilter
import com.example.risingproject.util.GlobalFunctions


class FilterUnderAdapter(private var context: Context, val items: InfoForUnderFilter, val filterItemClick : FilterItemClick, val rowNum : Int): BaseAdapter() {
    private var mCheckedPostion = -1

    fun postResult(itemNum : Int){
        filterItemClick.getSelectedItem(rowNum, itemNum)
    }

    override fun getCount(): Int = items.filters.size

    override fun getItem(p0: Int): Any = items.filters[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        when(items.type){
            "color" -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_color, p2, false)
                val container = view.findViewById<ConstraintLayout>(R.id.container_filter_color)
                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                name.text = item
                val img = view.findViewById<ImageView>(R.id.filter_category_under_color)
                val check = view.findViewById<ImageView>(R.id.filter_category_under_color_check)
                val colorImg = GlobalFunctions.StringToColorImg(item)
                img.setImageResource(colorImg)
                check.setImageResource(GlobalFunctions.StringToMarker(item))
                if(StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]){
                    check.visibility = View.VISIBLE
                }else{ check.visibility = View.GONE }

                val listener = View.OnClickListener {
                    StoreCategoryFragment.FilterBoolean.arr[rowNum][p0] = !StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]
                    if(StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]){
                        check.visibility = View.VISIBLE
                    }else{ check.visibility = View.GONE }
                    postResult(p0)
                }

                container.setOnClickListener(listener)
                img.setOnClickListener(listener)
                return view
            }
            "check" -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_check, p2, false)
                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                val check = view.findViewById<CheckBox>(R.id.filter_category_under_check)
                name.text = item

                check.isChecked = StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]
                val container = view.findViewById<ConstraintLayout>(R.id.container_filter_check)
                val listener = View.OnClickListener {
                    StoreCategoryFragment.FilterBoolean.arr[rowNum][p0] = !StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]
                    check.isChecked = StoreCategoryFragment.FilterBoolean.arr[rowNum][p0]
                    postResult(p0)
                }
                container.setOnClickListener(listener)
                check.setOnClickListener(listener)
                return view
            }
            else -> {
                val item = items.filters[p0]
                val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view : View = inflater.inflate(R.layout.recycler_item_filter_under_radio, p2, false)
                val name = view.findViewById<TextView>(R.id.filter_category_under_name)
                name.text = item
                val container = view.findViewById<ConstraintLayout>(R.id.container_filter_radio)
                val checkBox = view.findViewById<RadioButton>(R.id.filter_category_under_radio)

                checkBox.isChecked = (p0 == mCheckedPostion)
                val listener = View.OnClickListener {
                    if (p0 == mCheckedPostion) {
                        checkBox.isChecked = false
                        mCheckedPostion = -1
                    } else {
                        mCheckedPostion = p0
                        notifyDataSetChanged()
                    }

                    for(i in 0 until StoreCategoryFragment.FilterBoolean.arr[rowNum].size){
                        StoreCategoryFragment.FilterBoolean.arr[rowNum][i] = (i == p0)
                    }
                    postResult(p0)
                }
                container.setOnClickListener(listener)
                checkBox.setOnClickListener(listener)
                return view
            }
        }
    }
}