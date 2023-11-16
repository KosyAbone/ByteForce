package com.byteforce.kickash

import android.app.Application
import androidx.room.Room
import com.byteforce.kickash.data.db.AppDatabase
import com.byteforce.kickash.data.db.FbUserData

class KickAshApp:Application() {

    companion object {
        var db: AppDatabase? = null

        var globalUserData = FbUserData()

        fun getDatabase(): AppDatabase? {
            return db
        }


    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"KickAsh")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

}