package com.example.teamjejudo.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LikePlace::class], version = 1
)
abstract class LikePlaceDB : RoomDatabase() {
    abstract fun dao() : LikePlaceDao
}