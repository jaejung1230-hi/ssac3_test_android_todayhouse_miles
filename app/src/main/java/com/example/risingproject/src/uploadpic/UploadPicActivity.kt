package com.example.risingproject.src.uploadpic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.risingproject.R
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.src.uploadpic.addItemTag.AddItemTagActivity
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.databinding.ActivityUploadPicBinding
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.review.WriteReviewService
import com.example.risingproject.src.review.models.WriteReviewRequest
import com.example.risingproject.src.uploadpic.UploadPicActivity.save.listY
import com.example.risingproject.src.uploadpic.addItemTag.models.ResultItem
import com.example.risingproject.src.uploadpic.models.UploadPicRequest
import com.example.risingproject.src.uploadpic.util.KetWordAdapter
import com.example.risingproject.src.uploadpic.util.UploadPicService
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class UploadPicActivity: BaseActivity<ActivityUploadPicBinding>(ActivityUploadPicBinding::inflate),KeywordTagClick,UploadPicActivityView {
    val itemP = listOf<String>("10평 미만","10평대","20평대","30평대","40평대","50평대 이상")
    val itemL = listOf<String>("원룸&오피스텔","아파트","빌라&연립","단독주택","사무공간","상업공간","기타")
    val itemS = listOf<String>("모던","북유럽","빈티지","내추럴","프로방스&로맨틱","클래식&앤틱","한국&아시아","유니크","기타")
    val itemR = listOf<String>("원룸","침실","거실","주방","서재&작업실","아이방","베란다","욕실","현관","드레스룸","가구&소품","외관&기타","상업공간","사무공간")
    val key = ArrayList<String>()

    object save{
        var listX = ArrayList<Float>()
        var listY = ArrayList<Float>()
        var listID = ArrayList<Int>()
        var listItem = ArrayList<ResultItem>()
    }

    private lateinit var storage : FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storage = FirebaseStorage.getInstance()

        val uriStr = intent.getStringExtra("uri")
        val image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(uriStr))
        binding.imgUploadPic.setImageBitmap(image_bitmap)
        binding.imgUploadPic.setOnClickListener {
            val intent = Intent(this, AddItemTagActivity::class.java)
            intent.putExtra("uri",uriStr)
            startActivity(intent)
        }

        val pAdapter = ArrayAdapter(this, R.layout.spinner_item, itemP)
        binding.spinnerP.adapter = pAdapter
        binding.spinnerP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.parseColor("#7adbfe"))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val lAdapter = ArrayAdapter(this, R.layout.spinner_item, itemL)
        binding.spinnerL.adapter = lAdapter
        binding.spinnerL.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.parseColor("#7adbfe"))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val sAdapter = ArrayAdapter(this,R.layout.spinner_item, itemS)
        binding.spinnerS.adapter = sAdapter
        binding.spinnerS.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.parseColor("#7adbfe"))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val rAdapter = ArrayAdapter(this, R.layout.spinner_item, itemR)

        binding.spinnerR.adapter = rAdapter
        binding.spinnerR.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.recyclerKeyword.layoutManager = LinearLayoutManager(this)
        binding.recyclerKeyword.adapter = KetWordAdapter(this, key, this)

        binding.editKeyword.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.editKeyword.windowToken, 0)
                    key.add(binding.editKeyword.text.toString())
                    binding.editKeyword.text = null
                    binding.recyclerKeyword.adapter!!.notifyDataSetChanged()
                    return true
                }
                return false
            }
        })

        binding.btnUploadPic.setOnClickListener {
            showLoadingDialog(this)
            val now = System.currentTimeMillis()
            val date = Date(now)
            val sdf = SimpleDateFormat("yyyy_MM_DD_hh_mm_ss")
            val getTime = sdf.format(date)
            val filename = "${getTime}_${ApplicationClass.sSharedPreferences.getInt(ApplicationClass.LOG_IN_USER,-1)}.jpg"

            val storageRef : StorageReference = storage.reference
            val riversRef : StorageReference = storageRef.child("$filename.jpg")
            val uploadTask : UploadTask? = Uri.parse(uriStr)?.let { it1 -> riversRef.putFile(it1) }
            val photoUri = "https://firebasestorage.googleapis.com/v0/b/ssac-test-7751b.appspot.com/o/$filename.jpg?alt=media"

            uploadTask?.addOnSuccessListener {
                val uploadPicRequest = UploadPicRequest(binding.editContext.text.toString(), photoUri, itemP.indexOf(binding.spinnerP.selectedItem.toString())+1,
                    itemL.indexOf(binding.spinnerL.selectedItem.toString())+7,  itemS.indexOf(binding.spinnerS.selectedItem.toString())+13,
                    itemR.indexOf(binding.spinnerR.selectedItem.toString())+21, key, save.listX, save.listY, save.listID)
                UploadPicService(this).tryPostPic(ApplicationClass.sSharedPreferences.getInt(ApplicationClass.LOG_IN_USER,-1), uploadPicRequest)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(save.listX.size != 0){
            binding.tvAddTagNow.visibility = View.VISIBLE
            binding.tvAddTagNow.text = save.listX.size.toString()

            binding.tvAddTag.visibility = View.GONE
        }else{
            binding.tvAddTagNow.visibility = View.GONE
            binding.tvAddTag.visibility = View.VISIBLE
        }
    }

    override fun getSelectedTag(item: Int) {
        key.removeAt(item)
        binding.recyclerKeyword.adapter!!.notifyDataSetChanged()
    }

    override fun onPostPicSuccess(response: BaseResponse) {
        dismissLoadingDialog()
        finish()
    }

    override fun onPostPicFailure(message: String) {
        dismissLoadingDialog()
    }
}