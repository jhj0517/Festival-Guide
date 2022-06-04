package com.example.teamjejudo.retrofit

import com.example.teamjejudo.data.Festival
import com.example.teamjejudo.data.FestivalDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("searchFestival")
    fun getFestivals(
        @Query("serviceKey") serviceKey : String,
        @Query("MobileOS") mobileOS : String,
        @Query("MobileApp") mobileApp : String,
        @Query("eventStartDate") eventStartDate : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("_type") type: String
    ) : Call<Festival>

    @GET("detailCommon")
    fun getDetail(
        @Query("serviceKey") serviceKey : String,
        @Query("MobileOS") mobileOS: String,
        @Query("MobileApp") mobileApp: String,
        @Query("contentId") contentId : Int,
        @Query("defaultYN") defaultYN : String,
        @Query("firstImageYN") firstImageYN : String,
        @Query("addrinfoYN") addrinfoYN  :String,
        @Query("overviewYN") overviewYN  :String,
        @Query("_type") type: String
    ) : Call<FestivalDetail>
}