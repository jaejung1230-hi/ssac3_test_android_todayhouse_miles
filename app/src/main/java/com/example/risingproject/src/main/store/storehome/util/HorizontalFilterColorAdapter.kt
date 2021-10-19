package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class HorizontalFilterColorAdapter(private val context: Context, private val items: List<String>, val filterItemClick : FilterItemClick, val rowNum : Int): RecyclerView.Adapter<HorizontalFilterColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalFilterColorAdapter.ViewHolder {
        val binding = RecyclerItemFilterHorizontalColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalFilterColorAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position],filterItemClick, rowNum, position)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private var binding: RecyclerItemFilterHorizontalColorBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String, filterItemClick: FilterItemClick, rowNum: Int, itemNum: Int
        ){
            val colorImg = GlobalFunctions.StringToColorImg(item)
            binding.filterCategoryHorizontalColor.setImageResource(colorImg)
            binding.filterCategoryHorizontalColorCheck.setImageResource(GlobalFunctions.StringToMarker(item))
            if(StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]){
                binding.filterCategoryHorizontalColorCheck.visibility = View.VISIBLE
            }else{
                binding.filterCategoryHorizontalColorCheck.visibility = View.GONE
            }
            binding.filterCategoryHorizontalColor.setOnClickListener {
                StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum] = !StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]
                if(StoreCategoryFragment.FilterBoolean.arr[rowNum][itemNum]){
                    binding.filterCategoryHorizontalColorCheck.visibility = View.VISIBLE
                }else{
                    binding.filterCategoryHorizontalColorCheck.visibility = View.GONE
                }
                filterItemClick.getSelectedItem(rowNum, itemNum)
            }
        }
    }
}