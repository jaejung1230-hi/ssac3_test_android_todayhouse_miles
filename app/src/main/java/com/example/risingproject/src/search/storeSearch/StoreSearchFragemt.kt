package com.example.risingproject.src.search.storeSearch

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentStoreSearchBinding
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse
import com.example.risingproject.src.search.storeSearch.util.StoreSearchGridViewAdapter

class StoreSearchFragment : BaseFragment<FragmentStoreSearchBinding>(FragmentStoreSearchBinding::bind, R.layout.fragment_store_search), SearchFragmentView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stickyScrollViewInSearch.run {
            header = binding.containerStickyInSearch
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }
    }

    override fun onGetItemSearchSuccess(response: GetSearchItemResponse) {
        dismissLoadingDialog()
        Log.d("Search",response.toString())
        if(response.result.isNotEmpty()){
            binding.stickyScrollViewInSearch.visibility = View.VISIBLE
            binding.tvBlank.visibility = View.GONE
            binding.gridviewSearch.adapter = StoreSearchGridViewAdapter(requireContext(),response.result)
            binding.gridviewSearch.isExpanded = true
        }else{
            binding.stickyScrollViewInSearch.visibility = View.GONE
            binding.tvBlank.visibility = View.VISIBLE
        }
    }

    override fun onGetItemSearchFailure(message: String) {
        dismissLoadingDialog()
        Log.d("Search",message)
    }

    fun startSearch(str : String){
        SearchService(this).tryGetItemSearch(str)
        showLoadingDialog(requireContext())
    }
}