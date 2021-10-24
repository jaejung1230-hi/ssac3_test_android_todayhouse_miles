package com.example.risingproject.src.uploadpic

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityUploadPicBinding
import com.example.risingproject.src.uploadpic.addItemTag.AddItemTagActivity

class UploadPicActivity: BaseActivity<ActivityUploadPicBinding>(ActivityUploadPicBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uriStr = intent.getStringExtra("uri")
        val image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(uriStr))
        binding.imgUploadPic.setImageBitmap(image_bitmap)
        binding.imgUploadPic.setOnClickListener {
            val intent = Intent(this, AddItemTagActivity::class.java)
            intent.putExtra("uri",uriStr)
            startActivity(intent)
        }
    }
}