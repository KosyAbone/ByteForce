package com.byteforce.kickash.ui.main.social

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar
import kotlin.random.Random

class SocialViewModel : ViewModel() {

    /*
    private val _text = MutableLiveData<String>().apply {
        value = "This is social Fragment"
    }
    val text: LiveData<String> = _text

     */
    fun generateRandomTimestamp(): Long {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, -1)

        val currentTime = System.currentTimeMillis()

        val yesterdayTimestamp = calendar.timeInMillis

        return Random.nextLong(yesterdayTimestamp, currentTime)
    }

    private val preloadedData: List<SocialMessage> = listOf(
        SocialMessage("1", 1, "1", generateRandomTimestamp(), "hi"),
        SocialMessage("2", 2, "2", generateRandomTimestamp(), "hi"),
        SocialMessage("3", 3, "1", generateRandomTimestamp(), "How are you"),
        SocialMessage("4", 4, "2", generateRandomTimestamp(), "Fine"),
        SocialMessage("5", 5, "2", generateRandomTimestamp(), "And you?"),
        SocialMessage("6", 6, "1", generateRandomTimestamp(), "Not good. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim sit amet venenatis urna. Volutpat odio facilisis mauris sit amet massa vitae tortor. Mi quis hendrerit dolor magna eget est lorem. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus et. Viverra orci sagittis eu volutpat odio facilisis mauris sit. In massa tempor nec feugiat nisl pretium. Interdum posuere lorem ipsum dolor sit. Elementum nisi quis eleifend quam. Sed nisi lacus sed viverra. Sed elementum tempus egestas sed sed"),
        SocialMessage("7", 7, "2", generateRandomTimestamp(), "I see, good luck mate, must be hard going through that"),
    )

    val socialMessageList: MutableLiveData<List<SocialMessage>> = MutableLiveData<List<SocialMessage>>().apply {
        value = preloadedData
    };


}