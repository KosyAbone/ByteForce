package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.databinding.ActivityQuestionnaire1Binding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale





class Questionnaire1Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    var oldDate = ""

    fun initUI() {
        var currentDate = Date()

        // Get the calendar instance

        // Get the calendar instance
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTime(currentDate)
        calendar.add(Calendar.YEAR, -5)
        val fiveYearsAgo: Date = calendar.getTime()
        val formattedFiveYearsAgo: String = formatDate(fiveYearsAgo)
        Log.d("DateExample", "5 years ago: $formattedFiveYearsAgo")

        // 1 year before today

        // 1 year before today
        calendar.setTime(currentDate)
        calendar.add(Calendar.YEAR, -1)
        val oneYearAgo: Date = calendar.getTime()
        val formattedOneYearAgo: String = formatDate(oneYearAgo)
        Log.d("DateExample", "1 year ago: $formattedOneYearAgo")

        // 6 months before today

        // 6 months before today
        calendar.setTime(currentDate)
        calendar.add(Calendar.MONTH, -6)
        val sixMonthsAgo: Date = calendar.getTime()
        val formattedSixMonthsAgo: String = formatDate(sixMonthsAgo)
        Log.d("DateExample", "6 months ago: $formattedSixMonthsAgo")


        binding.btnOption1.setOnClickListener {
            var buttonText = binding.btnOption1.text.toString()
            oldDate = formattedFiveYearsAgo
            nextUI(buttonText)
        }

        binding.btnOption2.setOnClickListener {
            var buttonText = binding.btnOption2.text.toString()
            oldDate = formattedOneYearAgo
            nextUI(buttonText)

        }

        binding.btnOption3.setOnClickListener {
            var buttonText = binding.btnOption3.text.toString()
            oldDate = formattedSixMonthsAgo

            nextUI(buttonText)
        }
    }

    private fun formatDate(date: Date): String {
        // Define the desired format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Format the date
        return dateFormat.format(date)
    }

    fun nextUI(answer : String) {
        var username = "test"

        val sharedPrefs = this.getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()){
            putString(QuestionnaireConstants.question1 + username, answer)
            commit()
        }


        KickAshApp.globalUserData.questionnaire.startSmokingDate = oldDate




        val i = Intent(this@Questionnaire1Activity,Questionnaire2Activity::class.java)
        startActivity(i)
    }

    class QuestionnaireConstants{
        companion object{
            val question1 = "When did you start smoking? - "
            val question2 = "How many cigarettes do you consume daily on an average? - "
            val question3 = "What is your gender? - "
            val question4 = "How do you feel about smoking? - "
            val question5 = "What triggers you to smoke? - "
            val question6 = "What triggers you to smoke? - "

            val question7 = "What prompted your decision to quit smoking? - "
            val question8 = "What are your hobbies? - "
            val question9 = "By when do you want to see yourself free from smoking? - "
            val question10 = "How stressfull can you be."
        }
    }


}