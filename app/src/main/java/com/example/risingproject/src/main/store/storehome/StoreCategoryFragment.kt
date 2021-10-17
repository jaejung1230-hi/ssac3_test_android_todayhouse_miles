package com.example.risingproject.src.main.store.storehome

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentStoreCategoryBinding
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse

class StoreCategoryFragment :  BaseFragment<FragmentStoreCategoryBinding>(FragmentStoreCategoryBinding::bind, R.layout.fragment_store_category) ,
    StoreCategoryFragmentView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = getArguments()
        if(bundle != null){
            val name = bundle.getString("category")
            binding.toolbarCategoryTitle.text = name
        }

        StoreCategoryService(this).tryGetCategoryItem()
    }

    override fun onGetCategoryItemSuccess(response: GetCategoryItemResponse) {
        Log.d("test",response.toString())
    }

    override fun onGetCategoryItemFailure(message: String) {
        TODO("Not yet implemented")
    }
}