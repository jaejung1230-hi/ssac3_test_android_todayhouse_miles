package com.example.risingproject.src.detailStory

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityDetailStoryBinding
import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.uploadpic.UploadPicActivity

class DetailStortActivity : BaseActivity<ActivityDetailStoryBinding>(ActivityDetailStoryBinding::inflate),DetailStoryAcitivityView {
    val list = arrayListOf<keywords>()
    val listX = arrayListOf<Float>()
    val listY = arrayListOf<Float>()
    val listID = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoadingDialog(this)
        DetailStoryService(this).tryGetStory(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0),intent.getIntExtra("storyId",1))
    }

    override fun onGetDetailStorySuccess(response: GetDetailStoryResponse) {
        dismissLoadingDialog()
        Log.d("DetailStortActivity",response.result.toString())
        binding.tvArea.text = response.result[0].area
        binding.tvRoom.text = response.result[1].HousingType
        binding.tvStyle.text = response.result[2].style
        for (i in response.result[4].keywords){
            list.add(i)
        }
        Glide.with(this)
            .load(response.result[5].photo[0].photo)
            .error(R.drawable.temp_item_1)
            .into(binding.imgStoryPic)

        binding.tvExp.text = response.result[6].context[0].content

        for (i in response.result[7].pos){
            listX.add(i.posXs)
            listY.add(i.posYs)
            listID.add(i.itemIds.toInt())

            val imageView = ImageView(this@DetailStortActivity)
            imageView.setImageResource(R.drawable.ic_item_tag_marker)

            val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
            params.setMargins((binding.imgStoryPic.measuredWidth*i.posXs).toInt(), (binding.imgStoryPic.measuredHeight*i.posYs).toInt(), 0, 0)
            imageView.layoutParams = params

            binding.frameForDraw.addView(imageView)
        }


    }

    override fun onGetDetailStoryFailure(message: String) {
        Log.d("DetailStortActivity",message)
    }
}