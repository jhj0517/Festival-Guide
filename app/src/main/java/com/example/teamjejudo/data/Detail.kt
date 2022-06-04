package com.example.teamjejudo.data


import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("body")
        val body: Body,
        @SerializedName("header")
        val header: Header
    ) {
        data class Body(
            @SerializedName("items")
            val items: Items,
            @SerializedName("numOfRows")
            val numOfRows: Int,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("totalCount")
            val totalCount: Int
        ) {
            data class Items(
                @SerializedName("item")
                val item: Item
            ) {
                data class Item(
                    @SerializedName("bookingplace")
                    val bookingplace: String,
                    @SerializedName("contentid")
                    val contentid: Int,
                    @SerializedName("contenttypeid")
                    val contenttypeid: Int,
                    @SerializedName("discountinfofestival")
                    val discountinfofestival: String,
                    @SerializedName("eventenddate")
                    val eventenddate: Int,
                    @SerializedName("eventplace")
                    val eventplace: String,
                    @SerializedName("eventstartdate")
                    val eventstartdate: Int,
                    @SerializedName("festivaltype")
                    val festivaltype: String,
                    @SerializedName("placeinfo")
                    val placeinfo: String,
                    @SerializedName("playtime")
                    val playtime: String,
                    @SerializedName("program")
                    val program: String,
                    @SerializedName("progresstype")
                    val progresstype: String,
                    @SerializedName("sponsor1")
                    val sponsor1: String,
                    @SerializedName("sponsor1tel")
                    val sponsor1tel: String,
                    @SerializedName("sponsor2")
                    val sponsor2: String,
                    @SerializedName("sponsor2tel")
                    val sponsor2tel: String,
                    @SerializedName("subevent")
                    val subevent: String,
                    @SerializedName("usetimefestival")
                    val usetimefestival: String
                )
            }
        }

        data class Header(
            @SerializedName("resultCode")
            val resultCode: String,
            @SerializedName("resultMsg")
            val resultMsg: String
        )
    }
}