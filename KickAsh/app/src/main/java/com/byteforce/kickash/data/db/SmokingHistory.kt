package com.byteforce.kickash.data.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="smoking_history")
data class SmokingHistory(
    @SerializedName("smoked")
    var smoked: Boolean = false,
    @SerializedName("current_time")
    var currentTimeStamp: Double = 0.0,
    @SerializedName("relapsed")
    var relapsedStatus:Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = -1,
)

