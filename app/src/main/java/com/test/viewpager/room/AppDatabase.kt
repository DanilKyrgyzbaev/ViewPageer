package com.test.viewpager.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FragmentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fragmentDao(): FragmentDao
}