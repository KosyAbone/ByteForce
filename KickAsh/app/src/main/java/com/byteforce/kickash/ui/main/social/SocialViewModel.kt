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

    fun generateRandomTimestamp(): Long {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, -1)

        val currentTime = System.currentTimeMillis()

        val yesterdayTimestamp = calendar.timeInMillis

        return Random.nextLong(yesterdayTimestamp, currentTime)
    }

    private val messageApiCaller = MessageApiCaller(getApplication())


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
                if (context != null) {
                    Utils.showSimpleDialog("Sorry", "We are experiencing an unexpected issue, please try again later or contact us for assistance", context)
                }else {
                    error.postValue("We are experiencing an unexpected issue, please try again later or contact us for assistance")
                }
            }
        }
    }

}