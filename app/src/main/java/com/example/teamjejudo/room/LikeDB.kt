package com.example.teamjejudo.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Like::class], version = 1
)
abstract class LikeDB : RoomDatabase(){
    abstract fun dao() : LikeDao
}