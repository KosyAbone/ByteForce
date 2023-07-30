package com.byteforce.kickash.ui.main.social

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class SocialMessage(
    var _id: String,
    var senderId: String, //Probably the document id for the user, not sure what the format is yet
    var timestamp: Long, //Millisecond
    var messageBody: String
) {
    fun getTimeString(): String {
        val date = Date(this.timestamp)

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        return format.format(date)
    }
}
