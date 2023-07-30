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

    val messageApiCaller = MessageApiCaller(getApplication())


    val socialMessageList: MutableLiveData<List<SocialMessage>> = MutableLiveData<List<SocialMessage>>()

    init {
        viewModelScope.launch {

            try {
                val apiData: List<MessageModel> = withContext(Dispatchers.IO) {
                    messageApiCaller.getMessages(MessageGetQueryParams(null, null, null, null, null))
                }
                // Convert the API data to your SocialMessage list
                val socialMessages = apiData.map { messageModel ->
                    SocialMessage(
                        messageModel.id,
                        messageModel.userId,
                        messageModel.messageTimestamp.time,
                        messageModel.message
                    )
                }
                socialMessageList.value = socialMessages
            } catch (e: Exception) {
                Log.d("ERROR", "Unknown Error: $e")
                Utils.showSimpleDialog("Error", "Unknown Error", getApplication())
            }
        }
    }

}