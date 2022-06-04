package com.example.teamjejudo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LikeFestivalDao {
    @Query("SELECT * FROM `likefestival`")
    fun getAll() : List<LikeFestival>

    @Insert
    fun insert(vararg likePlace: LikeFestival)

    @Query("DELETE from LikeFestival where contentid=:place")
    fun delete(place : Int)
}