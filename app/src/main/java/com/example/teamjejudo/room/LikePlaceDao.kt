package com.example.teamjejudo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikePlaceDao {
    @Query("SELECT * FROM `likeplace`")
    fun getAll() : List<LikePlace>

    @Insert
    fun insert(vararg likePlace: LikePlace)

    @Query("DELETE from LikePlace where contentid=:place")
    fun delete(place : Int)
}