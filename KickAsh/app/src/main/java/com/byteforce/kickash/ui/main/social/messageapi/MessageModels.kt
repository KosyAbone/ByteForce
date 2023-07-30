package com.byteforce.kickash.ui.main.social.messageapi

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.Date

data class MessageListModel(
    @Json(name="page")
    val page: Int,
    @Json(name="totalItems")
    val totalItems: Int,
    @Json(name="messages")
    val messages: List<MessageModel>
)

@JsonClass(generateAdapter = true)
data class MessageModel (
    @Json(name="_id")
    val id: String,
    @Json(name="userId")
    val userId: String,
    @Json(name="messageTimestamp")
    val messageTimestamp: Date,
    @Json(name="message")
    val message: String
)

data class MessagePostModel(
    @Json(name="userId")
    val userId: String,
    @Json(name="message")
    val message: String
)

data class MessageGetQueryParams(
    val page: Int?,
    val startTime: String?,
    val endTime: String?,
    val userId: List<String>?,
    val message: String?
)

class DateAdapter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @FromJson
    fun fromJson(json: String): Date? {
        return try {
            dateFormat.parse(json)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    fun toJson(date: Date): String {
        return dateFormat.format(date)
    }
}