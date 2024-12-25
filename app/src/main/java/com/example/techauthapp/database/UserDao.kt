package com.example.techauthapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.techauthapp.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE username = :username AND password = :password LIMIT 1")
    suspend fun loginUser(username: String, password: String): User?
}
