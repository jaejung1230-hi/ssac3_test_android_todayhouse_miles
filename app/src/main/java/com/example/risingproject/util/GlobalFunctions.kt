package com.example.risingproject.util

import com.example.risingproject.R

object GlobalFunctions {

    fun StringToColorImg(colorName : String): Int{
        when(colorName){
            "화이트"->{ return R.drawable.color_white }
            "그레이"->{ return R.drawable.color_gray }
            "블랙"->{ return R.drawable.color_black }
            "베이지"->{ return R.drawable.color_begie }
            "브라운"->{ return R.drawable.color_brown }
            "실버"->{ return R.drawable.color_silver }
            "골드"->{ return R.drawable.color_gold }
            "레드"->{ return R.drawable.color_red }
            "오렌지"->{ return R.drawable.color_orange }
            "옐로우"->{ return R.drawable.color_yellow }
            "그린"->{ return R.drawable.color_green }
            "블루"->{ return R.drawable.color_blue }
            "네이비"->{ return R.drawable.color_navy }
            "바이올렛"->{ return R.drawable.color_violet }
            "핑크"->{ return R.drawable.color_pink }
            "투명"->{ return R.drawable.color_alpha }
            "밝은 우드톤"->{ return R.drawable.color_wood_bright }
            "중간 우드톤"->{ return R.drawable.color_wood_middle }
            "어두운 우드톤"->{ return R.drawable.color_wood_dark }
            else->{//멀티
                return R.drawable.color_multi
            }
        }
    }
    fun StringToMarker(colorName : String): Int{
        when(colorName){
            "화이트"->{ return R.drawable.ic_baseline_check_dark_12 }
            "베이지"->{ return R.drawable.ic_baseline_check_dark_12 }
            "실버"->{ return R.drawable.ic_baseline_check_dark_12 }
            "골드"->{ return R.drawable.ic_baseline_check_dark_12 }
            "옐로우"->{ return R.drawable.ic_baseline_check_dark_12 }
            "밝은 우드톤"->{ return R.drawable.ic_baseline_check_dark_12 }
            "핑크"->{ return R.drawable.ic_baseline_check_dark_12 }
            "투명"->{ return R.drawable.ic_baseline_check_dark_12 }
            else->{//멀티
                return R.drawable.ic_baseline_check_white_12
            }
        }
    }
}