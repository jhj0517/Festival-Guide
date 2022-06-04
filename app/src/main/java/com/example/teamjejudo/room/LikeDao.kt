package com.example.teamjejudo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeDao {
    @Query("SELECT placeId FROM `like`")
    fun getAll() : List<Int>

    @Insert
    fun insert(vararg like: Like)

    @Query("DELETE from `like` where placeId=:place")
    fun delete(place : Int)
}