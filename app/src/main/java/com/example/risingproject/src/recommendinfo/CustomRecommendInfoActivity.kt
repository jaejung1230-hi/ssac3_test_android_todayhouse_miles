package com.example.risingproject.src.recommendinfo

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityCustomRecommendInfoBinding
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.risingproject.R
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import android.widget.AdapterView



import android.widget.ArrayAdapter

class CustomRecommendInfoActivity: BaseActivity<ActivityCustomRecommendInfoBinding>(ActivityCustomRecommendInfoBinding::inflate) {
    var flag_page2_yet = true
    var flag_page3_yet = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarCustomInfo)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = ""

        binding.progressBarStep.isIndeterminate = false
        binding.progressBarStep.progress = 33

        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val height: Int = size.y

        val listLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)

        binding.constraintPage1.layoutParams = listLayoutParams
        binding.constraintPage2.layoutParams = listLayoutParams
        binding.constraintPage3.layoutParams = listLayoutParams

        //첫번째 페이지
        binding.linearPage1Apartment.setOnClickListener { firstInfoSet(binding.tvPage1Apartment, binding.linearPage1Apartment) }
        binding.linearPage1House.setOnClickListener { firstInfoSet(binding.tvPage1House, binding.linearPage1House) }
        binding.linearPage1Officetel.setOnClickListener { firstInfoSet(binding.tvPage1Officetel, binding.linearPage1Officetel) }
        binding.linearPage1Villa.setOnClickListener { firstInfoSet(binding.tvPage1Villa, binding.linearPage1Villa) }

        //두번째 페이지
        binding.tvPage2Size1.setOnClickListener { secondInfoSet(binding.tvPage2Size1) }
        binding.tvPage2Size2.setOnClickListener { secondInfoSet(binding.tvPage2Size2) }
        binding.tvPage2Size3.setOnClickListener { secondInfoSet(binding.tvPage2Size3) }
        binding.tvPage2Size4.setOnClickListener { secondInfoSet(binding.tvPage2Size4) }
        binding.tvPage2Size5.setOnClickListener { secondInfoSet(binding.tvPage2Size5) }

        //세번째 페이지
        binding.tvPage3Male.setOnClickListener { thirdGenderInfoSet(binding.tvPage3Male) }
        binding.tvPage3Female.setOnClickListener { thirdGenderInfoSet(binding.tvPage3Female) }

        binding.tvPage3Parent.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Parent) }
        binding.tvPage3Single.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Single) }
        binding.tvPage3Married.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Married) }
        binding.tvPage3Baby.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Baby) }
        binding.tvPage3Student.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Student) }
        binding.tvPage3Adult.setOnClickListener { thirdTypeInfoSet(binding.tvPage3Adult) }

        val templist = arrayListOf<String>()
        templist.add("선택해주세요.")
        for(i in 1900..2021){
            templist.add(i.toString())
        }

        val monthAdapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, templist)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAge.setAdapter(monthAdapter)

        binding.spinnerAge.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                if (!binding.spinnerAge.getItemAtPosition(position).equals("선택해주세요")) {
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_custom_recommend_info_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.menu_custom_recommend_info_toolbar_back -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun firstInfoSet(tv:TextView, li : LinearLayout){
        if(flag_page2_yet){
            binding.constraintPage2.visibility = View.VISIBLE
            binding.progressBarStep.progress = 66
            binding.tvNowStep.text = 2.toString()

            flag_page2_yet = false
        }

        binding.linearPage1Apartment.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage1Apartment.setTextColor(Color.parseColor("#4a4a4a"))

        binding.linearPage1House.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage1House.setTextColor(Color.parseColor("#4a4a4a"))

        binding.linearPage1Officetel.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage1Officetel.setTextColor(Color.parseColor("#4a4a4a"))

        binding.linearPage1Villa.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage1Villa.setTextColor(Color.parseColor("#4a4a4a"))

        tv.setTextColor(Color.parseColor("#7adbfe"))
        li.background = ContextCompat.getDrawable(this, R.drawable.selected_stroke_textview)

        binding.nestedScrollView.smoothScrollTo(0, binding.constraintPage1.bottom)
    }

    fun secondInfoSet(tv:TextView){
        if(flag_page3_yet){
            binding.constraintPage3.visibility = View.VISIBLE
            binding.progressBarStep.progress = 100
            binding.tvNowStep.text = 3.toString()

            flag_page3_yet = false

        }

        binding.tvPage2Size1.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage2Size1.setTextColor(Color.parseColor("#4a4a4a"))

        binding.tvPage2Size2.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage2Size2.setTextColor(Color.parseColor("#4a4a4a"))

        binding.tvPage2Size3.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage2Size3.setTextColor(Color.parseColor("#4a4a4a"))

        binding.tvPage2Size4.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage2Size4.setTextColor(Color.parseColor("#4a4a4a"))

        tv.setTextColor(Color.parseColor("#7adbfe"))
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_stroke_textview)

        binding.nestedScrollView.smoothScrollTo(0, binding.constraintPage2.bottom)
    }


    fun thirdTypeInfoSet(tv:TextView){

        binding.tvPage3Parent.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Parent.setTextColor(Color.parseColor("#4a4a4a"))
        binding.tvPage3Single.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Single.setTextColor(Color.parseColor("#4a4a4a"))
        binding.tvPage3Married.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Married.setTextColor(Color.parseColor("#4a4a4a"))
        binding.tvPage3Baby.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Baby.setTextColor(Color.parseColor("#4a4a4a"))
        binding.tvPage3Student.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Student.setTextColor(Color.parseColor("#4a4a4a"))
        binding.tvPage3Adult.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Adult.setTextColor(Color.parseColor("#4a4a4a"))

        tv.setTextColor(Color.parseColor("#7adbfe"))
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_stroke_textview)

    }

    fun thirdGenderInfoSet(tv:TextView){

        binding.tvPage3Male.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Male.setTextColor(Color.parseColor("#4a4a4a"))

        binding.tvPage3Female.background = ContextCompat.getDrawable(this, R.drawable.gray_stroke_corner_textview)
        binding.tvPage3Female.setTextColor(Color.parseColor("#4a4a4a"))

        tv.setTextColor(Color.parseColor("#7adbfe"))
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_stroke_textview)

    }



}