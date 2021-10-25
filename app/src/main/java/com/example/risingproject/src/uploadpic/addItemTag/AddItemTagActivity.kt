package com.example.risingproject.src.uploadpic.addItemTag

import android.annotation.SuppressLint
import android.app.Activity
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
import com.example.risingproject.src.uploadpic.addItemTag.models.ResultItem
import android.content.Intent
import com.example.risingproject.src.uploadpic.UploadPicActivity.save.listID
import com.example.risingproject.src.uploadpic.UploadPicActivity.save.listItem
import com.example.risingproject.src.uploadpic.UploadPicActivity.save.listX
import com.example.risingproject.src.uploadpic.UploadPicActivity.save.listY


class AddItemTagActivity: BaseActivity<ActivityAddItemTagBinding>(ActivityAddItemTagBinding::inflate), SelectTagClick {
    lateinit var slidePanel : SlidingUpPanelLayout
    var nowX by Delegates.notNull<Float>()
    var nowY by Delegates.notNull<Float>()

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
            var flag = true
            if (event.action == MotionEvent.ACTION_UP) {
                if(listImageView.size != 0){
                    for(i in 0..listImageView.size-1){
                        if(event.x >= listX[i]-0.05 && event.x < listX[i]+0.05 && event.y >= listY[i]-0.05 && event.y < listY[i]+0.05){
                            flag = false
                            break
                        }
                    }
                }
                if(flag){
                    nowX = (event.x / binding.imgAddTag.measuredWidth)
                    nowY = (event.y / binding.imgAddTag.measuredHeight)
                    slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                }else{
                    showCustomToast("다이얼로그")
                }

            }
            return@setOnTouchListener true
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

        binding.btnUploadPic.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        if(listX.size != 0){
            for(i in 0 until listX.size){
                Log.d("addtag",binding.imgAddTag.measuredWidth.toString())
                Log.d("addtag",listX[i].toString())
                Log.d("addtag",binding.imgAddTag.measuredHeight.toString())
                Log.d("addtag",listY[i].toString())
                val imageView = ImageView(this@AddItemTagActivity)
                imageView.setImageResource(R.drawable.ic_item_tag_marker)

                val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                params.setMargins((binding.imgAddTag.measuredWidth*listX[i]).toInt(), (binding.imgAddTag.measuredHeight*listY[i]).toInt(), 0, 0)
                imageView.layoutParams = params
                listImageView.add(imageView)
                binding.drawFrame.addView(imageView)
            }
        }
    }
    override fun getSelectedTag(item: ResultItem) {
        slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED

        val imageView = ImageView(this@AddItemTagActivity)
        imageView.setImageResource(R.drawable.ic_item_tag_marker)

        val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
        params.setMargins((binding.imgAddTag.measuredWidth*nowX).toInt(), (binding.imgAddTag.measuredHeight*nowY).toInt(), 0, 0)
        imageView.layoutParams = params

        listX.add(nowX)
        listY.add(nowY)
        listID.add(item.itemId)
        listItem.add(item)
        listImageView .add(imageView)

        binding.drawFrame.addView(imageView)
    }

}