package com.byteforce.kickash.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SmokingHistoryDao {

    @Query("SELECT * FROM smoking_history")
    fun getAllHistory(): List<SmokingHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllHistory(movies: List<SmokingHistory>)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun updateHistory(movie: SmokingHistory)

    @Query("Delete from smoking_history where id=:id")
    fun deleteHistory(id: Int)


}
