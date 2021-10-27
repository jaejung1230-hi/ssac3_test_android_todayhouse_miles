package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemChipFilterBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.models.ChipWithPos

class HorizontalForChipFilterdapter(private val context: Context, private val items: ArrayList<ChipWithPos>, val filterItemClick : FilterItemClick): RecyclerView.Adapter<HorizontalForChipFilterdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalForChipFilterdapter.ViewHolder {
        val binding = RecyclerItemChipFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalForChipFilterdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position],filterItemClick,position+1)

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemChipFilterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ChipWithPos, filterItemClick: FilterItemClick, rowNum: Int){
            binding.filterCategoryHorizontalChip.text = item.name
            binding.containerFilterCategoryHorizontalChip.setOnClickListener{
                StoreCategoryFragment.FilterBoolean.arr[item.pos1][item.pos2] = !StoreCategoryFragment.FilterBoolean.arr[item.pos1][item.pos2]
                filterItemClick.getSelectedItem(item.pos1, item.pos2)
            }


        }
    }
}