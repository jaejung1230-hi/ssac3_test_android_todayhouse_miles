package com.example.risingproject.src.uploadpic.addItemTag

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityAddItemTagBinding
import com.example.risingproject.src.uploadpic.addItemTag.models.GetSearchItemResponse
import com.example.risingproject.src.uploadpic.addItemTag.util.DialogRecyclerAdapter
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.risingproject.R

class AddItemTagActivity: BaseActivity<ActivityAddItemTagBinding>(ActivityAddItemTagBinding::inflate), SelectTagClick {
    lateinit var slidePanel : SlidingUpPanelLayout
    var nowX by Delegates.notNull<Float>()
    var nowY by Delegates.notNull<Float>()

    val listX = ArrayList<Float>()
    val listY = ArrayList<Float>()
    val listID = ArrayList<Int>()
    val listImageView = ArrayList<ImageView>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        slidePanel = binding.containerAddTag
        slidePanel.isTouchEnabled = false

        binding.btnBack.setOnClickListener {
            slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }

        val image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(intent.getStringExtra("uri")))
        binding.imgAddTag.setImageBitmap(image_bitmap)
        binding.imgAddTag.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                nowX = (event.x / binding.imgAddTag.measuredWidth)
                nowY = (event.y / binding.imgAddTag.measuredHeight)
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
            return@setOnTouchListener true
        }
        binding.imgAddTag.setOnClickListener {
            slidePanel.anchorPoint = 1.5f


        }
        binding.editSearchTag.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java).getItemSearch(s.toString()).enqueue(object : Callback<GetSearchItemResponse> {
                    override fun onResponse(call: Call<GetSearchItemResponse>, response: Response<GetSearchItemResponse>) {
                        if(response.body() != null){
                            Log.d("straaa",response.body()!!.result.toString())
                            binding.recyclerItemTags.layoutManager = LinearLayoutManager(this@AddItemTagActivity)
                            binding.recyclerItemTags.adapter = DialogRecyclerAdapter(this@AddItemTagActivity, response.body()!!.result, this@AddItemTagActivity)
                        }
                    }
                    override fun onFailure(call: Call<GetSearchItemResponse>, t: Throwable) {
                        showCustomToast("검색에 실패했습니다.")
                    }
                })
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    override fun getSelectedTag(itemId: Int) {
        slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        showCustomToast("선택완료. $itemId")

        val imageView = ImageView(this@AddItemTagActivity)
        imageView.setImageResource(R.drawable.ic_item_tag_marker)

        val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
        params.setMargins((binding.imgAddTag.measuredWidth*nowX).toInt(), (binding.imgAddTag.measuredHeight*nowY).toInt(), 0, 0)
        imageView.layoutParams = params

        listX.add(nowX)
        listY.add(nowX)
        listID.add(itemId)
        listImageView .add(imageView)

        binding.drawFrame.addView(imageView)
    }

}