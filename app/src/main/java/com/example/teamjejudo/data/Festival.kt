package com.example.teamjejudo.data

import com.google.gson.annotations.SerializedName

//http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival

data class Festival(
//    @SerializedName("title")
//    val festivalTitle: String,
//    @SerializedName("areacode")
//    val festivalArea: Int,
//    @SerializedName("eventstartdate")
//    val startDate: String,
//    @SerializedName("eventenddate")
//    val endDate: String,
//    @SerializedName("firstimage2")
//    val thumbnailImage: String
    val festivalTitle: String = "rsr",
    val festivalArea: Int = 3,
    val startDate: String = "123123",
    val endDate: String = "444",
    val thumbnailImage: String = "101010"
)