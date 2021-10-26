package com.example.risingproject.src.detailInto.buy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.risingproject.R
import com.example.risingproject.src.detailInto.buy.models.nameAndPrice
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.detailInto.BuyFragmentService
import com.example.risingproject.src.detailInto.buy.models.postCartRequest


class BuyBottomDialogFragment(context: Context, val itemId : Int) : BottomSheetDialogFragment(),ByFragmentView {
    val array_one = listOf<nameAndPrice>(nameAndPrice("01.오가닉프레쉬 무지(화이트) 차렵이불","38,900원 ~ 58,900원"),
        nameAndPrice("01.오가닉프레쉬 무지(오트밀) 차렵이불","48,900원 ~ 68,900원"),
        nameAndPrice("01.오가닉프레쉬 무지(베이지) 차렵이불","48,900원 ~ 68,900원"),
        nameAndPrice("컬러","")
    )
    val array_two = listOf<nameAndPrice>(nameAndPrice("슈퍼싱글(SS) 이불 단품","38900"),
        nameAndPrice("퀸(Q)겸용 이불 단품","58900"),
        nameAndPrice("사이즈","")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.bottomsheet_buy, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner_one = view.findViewById<Spinner>(R.id.spinner_one)
        val spinner_two = view.findViewById<Spinner>(R.id.spinner_two)

        val adapter_one: ArrayAdapter<nameAndPrice?> = object : ArrayAdapter<nameAndPrice?>(requireContext(), android.R.layout.simple_spinner_item, array_one) {
            override fun getCount(): Int {
                // to show hint "Select Gender" and don't able to select
                return array_one.size - 2
            }
        }

        val adapter_two: ArrayAdapter<nameAndPrice?> = object : ArrayAdapter<nameAndPrice?>(requireContext(), android.R.layout.simple_spinner_item, array_two) {
            override fun getCount(): Int {
                // to show hint "Select Gender" and don't able to select
                return array_one.size - 2
            }
        }
        spinner_one.adapter = adapter_one
        spinner_two.adapter = adapter_two

        spinner_one.setSelection(array_one.size-1)
        spinner_one.setSelection(array_two.size-1)


        val btn_cart = view.findViewById<TextView>(R.id.btn_cart)
        btn_cart.setOnClickListener {
            val postCartRequest = postCartRequest(itemId, 0, 0, 1 )
            BuyFragmentService(this).tryPostCart(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0),postCartRequest)
        }
    }

    override fun onGetbasketInfoSuccess(response: GetDetailInfoResponse) {

    }

    override fun onGetbasketInfoFailure(message: String) {

    }

    override fun onPostCartSuccess(response: BaseResponse) {
        dismiss()
    }

    override fun onPostCartFailure(message: String) {

    }
}