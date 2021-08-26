package com.gmail.kotlinhw23.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false )


abstract class HistoryDataBase : RoomDatabase () {
    abstract fun historyDao(): HistoryDao
}