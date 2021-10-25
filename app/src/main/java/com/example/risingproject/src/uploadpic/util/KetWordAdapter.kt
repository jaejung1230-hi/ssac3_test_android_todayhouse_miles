package com.example.risingproject.src.uploadpic.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemChipFilterBinding
import com.example.risingproject.databinding.RecyclerKeywordTextBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.models.ChipWithPos
import com.example.risingproject.src.uploadpic.KeywordTagClick

class KetWordAdapter(private val context: Context, private val items: ArrayList<String>, val filterItemClick : KeywordTagClick): RecyclerView.Adapter<KetWordAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerKeywordTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,items[position],filterItemClick,position)

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerKeywordTextBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String, keywordTagClick: KeywordTagClick, rowNum: Int){
            binding.tvKetword.text = item
            binding.containerKeyWord.setOnClickListener{
                keywordTagClick.getSelectedTag(rowNum)
            }
        }
    }
}