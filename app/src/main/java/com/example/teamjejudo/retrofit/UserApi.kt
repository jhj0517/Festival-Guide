package com.example.teamjejudo.retrofit

import com.example.teamjejudo.data.Festival
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
        @Query("_type") type: String
    ) : Call<Festival>

}