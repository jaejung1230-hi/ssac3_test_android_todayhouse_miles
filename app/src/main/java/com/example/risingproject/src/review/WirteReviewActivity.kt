package com.example.risingproject.src.review

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.databinding.ActivityWriteReviewBinding
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.detailInto.models.ItemInfo
import com.example.risingproject.src.review.models.WriteReviewRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody

import okhttp3.RequestBody
import retrofit2.http.Part
import java.io.File
import java.net.URI.create
import java.text.SimpleDateFormat
import java.util.*


class WirteReviewActivity : BaseActivity<ActivityWriteReviewBinding>(ActivityWriteReviewBinding::inflate), WriteReviewActivityView {
    private val GET_GALLERY_IMAGE = 200
    private var img_path : String? = null
    private var imageName : String? = null
    private lateinit var storage : FirebaseStorage
    var file : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage = FirebaseStorage.getInstance()

        val selectedItem = intent?.getParcelableExtra<ItemInfo?>("selectedItem")
        binding.tvSeletedItemName.text = selectedItem?.itemName

        binding.ratingbarPoint.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (rating<1.0f){
                binding.ratingbarPoint.rating = 1.0f
            }
            when(rating.toInt()){
                1 -> {binding.tvRating.text="별로에요."}
                2 -> {binding.tvRating.text="생각했던 것보다 별로에요."}
                3 -> {binding.tvRating.text="괜찮네요!"}
                4 -> {binding.tvRating.text="좋아요! 마음에 듭니다."}
                5 -> {binding.tvRating.text="마음에 쏙 들어요! 적극 추천~"}
            }
        }

        binding.editComment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               binding.tvEditLength.text = s?.length.toString()
            }
        })

        binding.imgUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }


        binding.btnPostReview.setOnClickListener {
            if(checkRate() || checkLength() || checkAgree()){
                showCustomToast("필수 입력사항을 확인해주세요")
            }
            else{
                val now = System.currentTimeMillis()
                val date = Date(now)
                val sdf = SimpleDateFormat("yyyy_MM_DD_hh_mm_ss")
                val getTime = sdf.format(date)
                val filename = "${getTime}_${ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,-1)}.jpg"

                val storageRef : StorageReference = storage.reference
                val riversRef : StorageReference = storageRef.child("$filename.jpg")
                val uploadTask : UploadTask? = file?.let { it1 -> riversRef.putFile(it1) }
                Log.d("fileTest",file.toString())
                val photoUri = "https://firebasestorage.googleapis.com/v0/b/ssac-test-7751b.appspot.com/o/$filename.jpg?alt=media"
                Log.d("photoUri",photoUri)
                uploadTask?.addOnSuccessListener {
                    //val writeReviewRequest = WriteReviewRequest(selectedItem!!.itemId!!, photoUri, binding.editComment.text.toString(),
                        //binding.ratingbarPoint.rating.toInt(),  binding.ratingbarPoint.rating.toInt(),  binding.ratingbarPoint.rating.toInt(),  binding.ratingbarPoint.rating.toInt())
                    //WriteReviewService(this).tryPostReview(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,-1), writeReviewRequest)
                }

            }
        }

    }

    fun checkRate(): Boolean {
        if(binding.ratingbarPoint.rating == 0.0f){
            binding.tvCheckRate.setTextColor(Color.parseColor("#f67a7c"))
            return true
        }
        else{
            binding.tvCheckRate.setTextColor(Color.parseColor("#000000"))
            return false
        }
    }

    fun checkLength(): Boolean {
        if(binding.editComment.text.length < 20){
            binding.tvCheckLength.setTextColor(Color.parseColor("#f67a7c"))
            return true
        }
        else{
            binding.tvCheckLength.setTextColor(Color.parseColor("#000000"))
            return false
        }
    }

    fun checkAgree(): Boolean {
        if(!binding.checkBoxAgree.isChecked){
            binding.tvCheckBox.setTextColor(Color.parseColor("#f67a7c"))
            return true
        }
        else{
            binding.tvCheckBox.setTextColor(Color.parseColor("#000000"))
            return false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            img_path = data.data?.let { getImagePathToUri(it) }
            file = data.data!!
            Log.d("fileTest",img_path.toString())
            Log.d("fileTest",file.toString())

            val image_bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            binding.imgUpload.setImageBitmap(image_bitmap)
        }
    }

    fun getImagePathToUri(data : Uri):String {

        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = managedQuery(data, proj, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val imgPath:String = cursor.getString(column_index)
        val imgName :String = imgPath.substring(imgPath.lastIndexOf("/") + 1)
        Log.d("test", "이미지 이름 : " + imgName)
        this.imageName = imgName

        return imgPath
    }

    override fun onPostWriteReviewSuccess(response: BaseResponse) {
        Log.d("testaa",response.toString())
        showCustomToast("등록이 완료되었습니다.")
        finish()
    }

    override fun onPostWriteReviewFailure(message: String) {
        Log.d("testaa",message)
        showCustomToast("등록에 실패했습니다. $message")
    }
}