package com.example.teamjejudo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Like(
    @ColumnInfo(name = "placeId") var placeId: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var count: Int = 0
}
