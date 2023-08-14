package com.byteforce.kickash.ui.main.social

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byteforce.kickash.ui.main.social.messageapi.MessageApiCaller
import com.byteforce.kickash.ui.main.social.messageapi.MessageApiService
import com.byteforce.kickash.ui.main.social.messageapi.MessageGetQueryParams
import com.byteforce.kickash.ui.main.social.messageapi.MessageModel
import com.byteforce.kickash.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import kotlin.random.Random

class SocialViewModel(application: Application) : AndroidViewModel(application) {


    private var modelReady = false

    private val messageApiCaller = MessageApiCaller(getApplication())
    private val existingMessageIds = mutableSetOf<String>()

    val socialMessageList: MutableLiveData<List<SocialMessage>> = MutableLiveData<List<SocialMessage>>()

    val error: MutableLiveData<String?> = MutableLiveData()
    init {
        viewModelScope.launch {
            var retries = 0
            val maxRetries = 3

            while (retries < maxRetries) {
                try {
                    val apiData: List<MessageModel> = withContext(Dispatchers.IO) {
                        messageApiCaller.getMessages(MessageGetQueryParams(null, null, null, null, null))
                    }
                    val socialMessages = apiData.map { messageModel ->
                        SocialMessage(
                            messageModel.id,
                            messageModel.userId,
                            messageModel.messageTimestamp.time,
                            messageModel.message
                        )
                    }
                    socialMessageList.value = socialMessages
                    existingMessageIds.addAll(socialMessages.map { it.id })
                    // Break out of the loop if data is successfully retrieved
                    break
                } catch (e: Exception) {
                    Log.d("ERROR", "Unknown Error: $e")
                    retries++
                    delay(1000) // Wait for 1 second before retrying
                }
            }

            if (retries == maxRetries) {
                val context = getApplication<Application>()
                error.postValue("We are experiencing an unexpected issue, please try again later or contact us for assistance")

            }
            modelReady = true
        }
    }




    fun oldMessagePoll(page: Int) {
        if (!modelReady) {
            return
        }
        viewModelScope.launch {
            try {
                val apiData: List<MessageModel> = withContext(Dispatchers.IO) {
                    messageApiCaller.getMessages(MessageGetQueryParams(page, null, null, null, null))
                }
                val socialMessages = apiData.map { messageModel ->
                    SocialMessage(
                        messageModel.id,
                        messageModel.userId,
                        messageModel.messageTimestamp.time,
                        messageModel.message
                    )
                }
                appendSocialData(socialMessages.filter { !existingMessageIds.contains(it.id) })
            } catch (e: Exception) {
                Log.d("ERROR", "Unknown Error: $e")
                    error.postValue("Message update failed")
            }
        }
    }

    suspend fun sendMessage(senderId: String, message: String): Boolean {
        if (!modelReady) {
            return false
        }
        var result = false
        try {
            val responseMessage = messageApiCaller.sendMessage(senderId, message)
            if (responseMessage != null) {
                val socialMessage = SocialMessage(
                    responseMessage.id,
                    responseMessage.userId,
                    responseMessage.messageTimestamp.time,
                    responseMessage.message
                )
                prependSocialData(listOf(socialMessage))
                result = true

            }else {
                    error.postValue("Message delivery failed")
            }
        } catch (e: Exception) {
            Log.d("ERROR", "Unknown Error: $e")
                error.postValue("Message delivery failed")
        }
        return result
    }

    private fun appendSocialData(newData: List<SocialMessage>) {
        existingMessageIds.addAll(newData.map { it.id })
        socialMessageList.value = socialMessageList.value.orEmpty() + newData
    }

    private fun prependSocialData(newData: List<SocialMessage>) {
        existingMessageIds.addAll(newData.map { it.id })
        socialMessageList.value = newData + socialMessageList.value.orEmpty()
    }
    fun newMessagePoll(page: Int = 1) {
        if (!modelReady) {
            return
        }
        viewModelScope.launch {
            try {
                val apiData: List<MessageModel> = withContext(Dispatchers.IO) {
                    messageApiCaller.getMessages(MessageGetQueryParams(page, null, null, null, null))
                }
                val socialMessages = apiData.map { messageModel ->
                    SocialMessage(
                        messageModel.id,
                        messageModel.userId,
                        messageModel.messageTimestamp.time,
                        messageModel.message
                    )
                }
                prependSocialData(socialMessages.filter { !existingMessageIds.contains(it.id) })
            } catch (e: Exception) {
                Log.d("ERROR", "Unknown Error: $e")
                    error.postValue("Message update failed")
            }
        }
    }
}