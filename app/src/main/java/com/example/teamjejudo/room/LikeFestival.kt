package com.example.teamjejudo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikeFestival(
    @ColumnInfo(name = "addr1")
    val addr1: String?,
    @ColumnInfo(name = "addr2")
    val addr2: String?,
    @ColumnInfo(name = "areacode")
    val areacode: Int?,
    @ColumnInfo(name = "cat1")
    val cat1: String?,
    @ColumnInfo(name = "cat2")
    val cat2: String?,
    @ColumnInfo(name = "cat3")
    val cat3: String?,
    @ColumnInfo(name = "contentid")
    val contentid: Int?,
    @ColumnInfo(name = "contenttypeid")
    val contenttypeid: Int?,
    @ColumnInfo(name = "createdtime")
    val createdtime: Long?,
    @ColumnInfo(name = "eventenddate")
    val eventenddate: Int?,
    @ColumnInfo(name = "eventstartdate")
    val eventstartdate: Int?,
    @ColumnInfo(name = "firstimage")
    val firstimage: String?,
    @ColumnInfo(name = "firstimage2")
    val firstimage2: String?,
    @ColumnInfo(name = "mapx")
    val mapx: Double?,
    @ColumnInfo(name = "mapy")
    val mapy: Double?,
    @ColumnInfo(name = "mlevel")
    val mlevel: Int?,
    @ColumnInfo(name = "modifiedtime")
    val modifiedtime: Long?,
    @ColumnInfo(name = "readcount")
    val readcount: Int?,
    @ColumnInfo(name = "sigungucode")
    val sigungucode: Int?,
    @ColumnInfo(name = "tel")
    val tel: String?,
    @ColumnInfo(name = "title")
    val title: String?
) {
    @PrimaryKey(autoGenerate = true)
    var count: Int = 0

}
