package com.example.onlineshopsatriaadhipradana.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.local.dao.UserDao
import com.example.data.storage.local.db.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder (
            context,
            AppDataBase::class.java,
            "AppDataBase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDataBase: AppDataBase): UserDao = appDataBase.userDao

}