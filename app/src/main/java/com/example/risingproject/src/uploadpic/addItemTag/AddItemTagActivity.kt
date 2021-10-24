package com.example.risingproject.src.uploadpic.addItemTag

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityAddItemTagBinding
import com.example.risingproject.src.uploadpic.addItemTag.models.GetSearchItemResponse
import com.example.risingproject.src.uploadpic.addItemTag.util.DialogRecyclerAdapter
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddItemTagActivity: BaseActivity<ActivityAddItemTagBinding>(ActivityAddItemTagBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(intent.getStringExtra("uri")))
        binding.imgAddTag.setImageBitmap(image_bitmap)
        binding.imgAddTag.setOnClickListener {
            val dialog = DialogPlus.newDialog(this)
                .setContentHolder(ViewHolder(R.layout.search_top_sheet))
                .setExpanded(true,250)
                .setGravity(Gravity.TOP)
                .setCancelable(false)
                .setOnClickListener { dialog, view ->
                    if(view.id == R.id.btn_back){
                        dialog.dismiss()
                    }

                    ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java).getItemSearch("유기농").enqueue(object : Callback<GetSearchItemResponse> {
                        override fun onResponse(call: Call<GetSearchItemResponse>, response: Response<GetSearchItemResponse>) {
                            Log.d("test",response.toString())
                            val recycler_item_tags =view.findViewById<RecyclerView>(R.id.recycler_item_tags)
                            recycler_item_tags.adapter = DialogRecyclerAdapter(this@AddItemTagActivity, response.body()!!.result)
                        }

                        override fun onFailure(call: Call<GetSearchItemResponse>, t: Throwable) {
                            showCustomToast("검색에 실패했습니다.")
                        }

                    })
                }
                .setOnItemClickListener{ dialogPlus: DialogPlus, any: Any, view: View, i: Int ->
                    val btn_back = view.findViewById<ImageView>(R.id.btn_back)
                    btn_back.setOnClickListener {
                        //
                        showCustomToast("클릭확인")
                    }
                }
                .create()
            dialog.show()

        }
    }
}