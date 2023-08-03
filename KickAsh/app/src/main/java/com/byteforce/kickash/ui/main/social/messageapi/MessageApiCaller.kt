package com.byteforce.kickash.ui.main.social.messageapi

import android.content.Context
import android.util.Log
import com.byteforce.kickash.utils.Utils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.ref.WeakReference

class MessageApiCaller {
    private var activityContext: WeakReference<Context>? = null

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateAdapter()).build()

    //Debug
/*
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create an instance of OkHttpClient and add the logging interceptor
    val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // Add any other configurations to the OkHttpClient as needed
        .build()

 */
    //End debug

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://byteforce-api.onrender.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        //.client(httpClient) // Set the custom OkHttpClient
        .build()

    private val messageApiService: MessageApiService = retrofit.create(MessageApiService::class.java)

    constructor(context: Context) {
        this.activityContext = WeakReference(context)
    }

    fun getContext(): Context? {
        return this.activityContext?.get()
    }

    suspend fun getMessages(params: MessageGetQueryParams): List<MessageModel> {

        val (page, startTime, endTime, userId, message) = params
        return withContext(Dispatchers.IO) {
            val response = messageApiService.getMessages(page, startTime, endTime, userId, message).execute()
            if (response.isSuccessful) {

                val responseBody: MessageListModel? = response.body()

                if (responseBody != null) {
                    if (responseBody.messages.isEmpty()) {

                        withContext(Dispatchers.Main) {
                            getContext()?.let { Utils.showSimpleToast("No messages found", it) }
                        }
                    }
                }
                responseBody?.messages ?: emptyList()
            } else {
                Log.d("ERROR", "Failed to get message data from remote: ${response.code()}")

                withContext(Dispatchers.Main) {
                    getContext()?.let { Utils.showSimpleDialog("Error", "Unknown Error", it) }
                }
                emptyList()
            }
        }
    }

    suspend fun sendMessage(userId: String, message: String): MessageModel? {
        return withContext(Dispatchers.IO) {
            val response = messageApiService.sendMessage(MessagePostModel(userId, message)).execute()
            if (response.isSuccessful) {

                val responseBody: MessageModel? = response.body()

                if (responseBody == null) {

                    withContext(Dispatchers.Main) {
                        getContext()?.let { Utils.showSimpleToast("Failed to deliver message", it) }
                    }
                }
                responseBody
            } else {
                Log.d("ERROR", "Failed to get message data from remote: ${response.code()}")
                withContext(Dispatchers.Main) {
                    getContext()?.let { Utils.showSimpleDialog("Error", "Unknown Error", it) }
                }
                null
            }
        }
    }
}