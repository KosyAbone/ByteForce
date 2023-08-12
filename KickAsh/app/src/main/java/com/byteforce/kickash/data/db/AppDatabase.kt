package com.byteforce.kickash.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SmokingHistory::class, UserModelItem::class, QuestionairData::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): SmokingHistoryDao

    abstract fun userDao(): UserDao
}
