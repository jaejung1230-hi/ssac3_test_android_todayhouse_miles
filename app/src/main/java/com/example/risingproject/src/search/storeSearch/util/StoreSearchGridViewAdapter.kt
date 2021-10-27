package com.example.risingproject.src.search.storeSearch.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.search.storeSearch.models.ResultItem
import java.text.DecimalFormat

class StoreSearchGridViewAdapter(private var context: Context, val items: List<ResultItem>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(p0: Int): Any = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val item = items[p0]
        val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.recycler_item_for_big, p2, false)
        val img_item_big = view.findViewById<ImageView>(R.id.img_item_big)
        val tv_company_name = view.findViewById<TextView>(R.id.tv_company_name_big)
        val tv_item_title = view.findViewById<TextView>(R.id.tv_item_title_big)
        val tv_item_percent = view.findViewById<TextView>(R.id.tv_item_percent_big)
        val tv_item_price = view.findViewById<TextView>(R.id.tv_item_price_big)
        val tv_item_rate = view.findViewById<TextView>(R.id.tv_item_rate_big)
        val tv_item_reviews = view.findViewById<TextView>(R.id.tv_item_reviews_big)
        val tv_item_special_price = view.findViewById<TextView>(R.id.tv_item_special_price_big)
        val tv_item_delivery_free = view.findViewById<TextView>(R.id.tv_item_delivery_free_big)
        val container_big = view.findViewById<ConstraintLayout>(R.id.container_big)

        container_big.setOnClickListener {
            val intent = Intent(context, DetailInfoActivity::class.java)
            intent.putExtra("itemId",item.itemId)
            context.startActivity(intent)
        }

        Glide.with(context).load(item.mainPhoto)
            .error(R.drawable.temp_item_1)
            .into(img_item_big)

        tv_company_name.text = item.companyName
        tv_item_title.text = item.itemName
        tv_item_percent.text = item.percenttage.toString()
        val t_dec_up = DecimalFormat("###,###,###")
        tv_item_price.text = t_dec_up.format(item.price)
        tv_item_rate.text = item.reviewRate.toString()
        tv_item_reviews.text = item.numOfReviews.toString()

        if(item.percenttage >= 50){
            tv_item_special_price.visibility = View.VISIBLE
        }

        return view
    }
}