package com.byteforce.kickash.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModelItem(
    @PrimaryKey(autoGenerate = true)
    var userId:Int = 0,
    var firstName:String = "",
    var lastName: String = "",
    var email:String = "",
    var userName:String = "",
    var password:String = ""
)