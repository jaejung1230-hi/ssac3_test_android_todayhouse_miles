package com.example.risingproject.src.main.store

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentStoreBinding
import com.example.risingproject.src.main.store.util.StoreFragmentViewPagerAdapter
import com.example.risingproject.src.search.SearchActivity
import com.google.android.material.tabs.TabLayoutMediator

class StoreFragemt : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::bind, R.layout.fragment_store) {
    val ListCategory = listOf<String>("스토어홈","베스트","오늘의딜","방한템특가","지정일배송","프리미엄","기획전")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.viewpagerStoreFragment.apply {
            adapter = StoreFragmentViewPagerAdapter(context as FragmentActivity)
        }

        TabLayoutMediator(binding.tabsStoreFragment,binding.viewpagerStoreFragment){ tab, position ->
            tab.text = ListCategory[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bookmark_and_basket, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}