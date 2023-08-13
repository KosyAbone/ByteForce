package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.databinding.ActivityQuestionnaire9Binding
import com.byteforce.kickash.databinding.ActivityQuestionnaireEndBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class Questionnaire9Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire9Binding

    private lateinit var selectedDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire9Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        // Set the minimum date (30 days from today)
        val minCalendar = Calendar.getInstance()
        minCalendar.add(Calendar.DAY_OF_MONTH, 30)
        binding.calendarView.minDate = minCalendar.timeInMillis

        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            // Update the selectedDate
            val selectedGoal = Calendar.getInstance()
            selectedGoal.set(year, month, day)
            selectedDate = selectedGoal.time

            Log.i("Goal Date", selectedDate.toString())
        }

        binding.btnNext.setOnClickListener {
            nextUI(selectedDate)
        }
    }

    fun nextUI(answer : Date) {
        var username = "test"

        val sharedPrefs = this.getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()){
            putString(Questionnaire1Activity.QuestionnaireConstants.question9 + username, answer.toString())
            commit()
        }

        val i = Intent(this,QuestionnaireEndActivity::class.java)
        startActivity(i)
    }

}