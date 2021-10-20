package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalTextBinding
import com.example.risingproject.databinding.RecyclerItemTenFilterBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment

class HorizontalForTenFilterdapter(private val context: Context, private val items: List<String>, val filterItemClick : FilterItemClick): RecyclerView.Adapter<HorizontalForTenFilterdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalForTenFilterdapter.ViewHolder {
        val binding = RecyclerItemTenFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalForTenFilterdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position],filterItemClick,position+1)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemTenFilterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String, filterItemClick: FilterItemClick, rowNum: Int){
            binding.filterCategoryHorizontalText.text = item
            binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#dadada"))
            binding.containerFilterCategoryHorizontalText.strokeColor = (Color.parseColor("#dadada"))
            binding.filterCategoryHorizontalText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_arrow_forward_ios_14, 0)
            for(i in StoreCategoryFragment.FilterBoolean.arr[rowNum]){
                if(i){
                    binding.filterCategoryHorizontalText.setTextColor(Color.parseColor("#4d4a69"))
                    binding.containerFilterCategoryHorizontalText.strokeColor = (Color.parseColor("#4d4a69"))
                    binding.filterCategoryHorizontalText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_arrow_forward_ios_main_14, 0)
                    break
                }
            }
            binding.containerFilterCategoryHorizontalText.setOnClickListener {
                filterItemClick.getDrawer(rowNum)
            }
        }
    }
}