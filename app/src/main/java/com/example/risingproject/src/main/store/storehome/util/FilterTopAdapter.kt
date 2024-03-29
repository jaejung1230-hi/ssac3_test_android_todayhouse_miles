package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemFilterCategoryTopBinding
import com.example.risingproject.src.main.store.storehome.models.InfoForUnderFilter

class FilterTopAdapter(private val context: Context, private val items: List<String>, private val underItems : List<InfoForUnderFilter> ,
                       private val filterItemClick : FilterItemClick, private val pageNum : Int): RecyclerView.Adapter<FilterTopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterTopAdapter.ViewHolder {
        val binding = RecyclerItemFilterCategoryTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterTopAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position], underItems[position], filterItemClick, position, position == pageNum)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemFilterCategoryTopBinding) : RecyclerView.ViewHolder(binding.root){
        var open_yet = true
        fun bind(context: Context, item: String, underItems: InfoForUnderFilter, filterItemClick: FilterItemClick, p0: Int, b: Boolean){
            binding.filterName.text = item
            binding.filterUnder.isExpanded = true

            if(underItems.type.equals("radio")){
                binding.filterUnder.numColumns = 1
            }else{
                binding.filterUnder.numColumns = 2
            }
            if(b){
                open_yet = false
            }
            if(open_yet){
                binding.filterImg.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                binding.filterUnder.visibility = View.GONE
            }else{
                binding.filterImg.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_main_24)
                binding.filterUnder.visibility = View.VISIBLE
            }

            binding.filterUnder.adapter = FilterUnderAdapter(context,underItems, filterItemClick, p0)
            binding.filterUnder.isExpanded = true

            binding.containerFilter.setOnClickListener {
                if(open_yet){
                    binding.filterImg.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_main_24)
                    binding.filterUnder.visibility = View.VISIBLE
                }else{
                    binding.filterImg.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.filterUnder.visibility = View.GONE
                }
                open_yet = !open_yet
            }
        }
    }
}