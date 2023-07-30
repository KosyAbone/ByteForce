package com.byteforce.kickash.ui.main.social.messageapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MessageApiService {
    @GET("/messages")
    fun getMessages(
        @Query("page")
        page: Int?,
        @Query("start")
        startTime: String?,
        @Query("end")
        endTime: String?,
        @Query("userId")
        userId: List<String>?,
        @Query("message")
        message: String?
    ): Call<MessageListModel>

    @POST("/messages")
    fun sendMessage(@Body messagePost: MessagePostModel): Call<MessageModel>
}