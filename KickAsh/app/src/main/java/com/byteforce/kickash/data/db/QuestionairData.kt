package com.byteforce.kickash.data.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="questionair_data")
data class QuestionairData(
    @SerializedName("question")
    var question: Boolean = false,
    @SerializedName("anser")
    var answer: String = "",
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = -1,
)

