package com.byteforce.kickash.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table where ( userName = :username or email = :username) and password = :password ")
    fun getUser(username:String, password:String): UserModelItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(movies: UserModelItem)

}
