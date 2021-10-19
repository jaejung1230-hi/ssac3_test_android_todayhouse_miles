package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalTextBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment

class HorizontalTextColorAdapter(private val context: Context, private val items: List<String>, val filterItemClick : FilterItemClick, val rowNum : Int): RecyclerView.Adapter<HorizontalTextColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalTextColorAdapter.ViewHolder {
        val binding = RecyclerItemFilterHorizontalTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalTextColorAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position],filterItemClick, rowNum, position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemFilterHorizontalTextBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String, filterItemClick: FilterItemClick, rowNum: Int, itemNum: Int){
            binding.filterCategoryHorizontalText.text = item
            if(StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]){
                binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#4d4a69"))
                binding.filterCategoryHorizontalClose.visibility = View.VISIBLE
            }else{
                binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#4a4a4a"))
                binding.filterCategoryHorizontalClose.visibility = View.GONE
            }
            binding.filterCategoryHorizontalText.setOnClickListener {
                StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum] = !StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]
                if(StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]){
                    binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#4d4a69"))
                    binding.filterCategoryHorizontalClose.visibility = View.VISIBLE
                }else{
                    binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#4a4a4a"))
                    binding.filterCategoryHorizontalClose.visibility = View.GONE
                }
                filterItemClick.getSelectedItem(rowNum, itemNum)
            }
        }
    }
}