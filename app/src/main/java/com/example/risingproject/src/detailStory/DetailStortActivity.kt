package com.example.risingproject.src.detailStory

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityDetailStoryBinding
import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import com.example.risingproject.src.detailStory.models.idAndPic
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.detailStory.util.HorizontalHashTagAdapter
import com.example.risingproject.src.detailStory.util.HorizontalItemTagAdapter
import com.example.risingproject.src.detailStory.util.HorizontalTempAdapter
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.main.home.homemain.util.BestPicRecyclerAdapter
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.ResultItemAll
import com.example.risingproject.src.main.store.storehome.util.StoreHomeRecordAdapter
import com.example.risingproject.src.uploadpic.UploadPicActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.risingproject.src.detailStory.util.SamePicAdapter


class DetailStortActivity : BaseActivity<ActivityDetailStoryBinding>(ActivityDetailStoryBinding::inflate),DetailStoryAcitivityView {
    val list = arrayListOf<keywords>()
    val listX = arrayListOf<Float>()
    val listY = arrayListOf<Float>()
    val listID = arrayListOf<idAndPic>()

    val tempItmePic = listOf<Int>(R.drawable.temp_item_1, R.drawable.temp_item_2, R.drawable.temp_item_3, R.drawable.temp_item_4,
        R.drawable.temp_item_5, R.drawable.temp_item_6, R.drawable.temp_item_7)

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

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerKetwords.layoutManager = linearLayoutManager
        binding.recyclerKetwords.adapter = HorizontalHashTagAdapter(this,list)

        Glide.with(this)
            .load(response.result[5].photo[0].photo)
            .error(R.drawable.temp_item_1)
            .into(binding.imgStoryPic)

        binding.tvExp.text = response.result[6].context[0].content

        for (i in response.result[7].pos){
            listX.add(i.posXs)
            listY.add(i.posYs)
            listID.add(idAndPic(i.itemIds.toInt(),i.photoUrl))

            val imageView = ImageView(this@DetailStortActivity)
            imageView.setImageResource(R.drawable.ic_item_tag_marker)

            val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
            params.setMargins((binding.imgStoryPic.measuredWidth*i.posXs).toInt(), (binding.imgStoryPic.measuredHeight*i.posYs).toInt(), 0, 0)
            imageView.layoutParams = params

            binding.frameForDraw.addView(imageView)
        }

        val linearLayoutManager2 = LinearLayoutManager(this)
        linearLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerTags.layoutManager = linearLayoutManager2
        binding.recyclerTags.adapter = HorizontalItemTagAdapter(this,listID)

        val linearLayoutManager3 = LinearLayoutManager(this)
        linearLayoutManager3.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerTemp.layoutManager = linearLayoutManager3
        binding.recyclerTemp.adapter = HorizontalTempAdapter(this,tempItmePic)

        DetailStoryService(this).tryGetPic()
    }

    override fun onGetDetailStoryFailure(message: String) {
        Log.d("DetailStortActivity",message)
    }

    override fun onGetPicsSuccess(response: getPicResponse) {
        Log.d("DetailStortActivity",response.result.toString())
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerSamePic.layoutManager = staggeredGridLayoutManager
        binding.recyclerSamePic.adapter = SamePicAdapter(this,response.result)
    }

    override fun onGetPicsFailure(message: String) {
        Log.d("DetailStortActivity",message)
    }
}