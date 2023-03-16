package com.example.data.storage.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.UserData
import com.example.data.storage.local.dao.UserDao

@Database (
    entities = [UserData::class],
    version = 1,
)
abstract class AppDataBase: RoomDatabase() {
    abstract val userDao: UserDao
}