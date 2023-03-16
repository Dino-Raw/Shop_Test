package com.example.data.storage.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.entity.UserData

@Dao
interface UserDao {
    @Insert
    suspend fun insert(userData: UserData)

    @Query("SELECT * FROM user_table WHERE first_name LIKE :firstName AND password LIKE :password")
    fun getUserByFirstNameAndPassword(firstName: String, password: String): UserData?

    @Query("SELECT * FROM user_table WHERE email LIKE :email")
    fun  getUserByEmail(email: String): UserData?
}