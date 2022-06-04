package com.example.teamjejudo.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LikeFestival::class], version = 1
)
abstract class LikeFestivalDB : RoomDatabase() {
   abstract fun dao(): LikeFestivalDao
}