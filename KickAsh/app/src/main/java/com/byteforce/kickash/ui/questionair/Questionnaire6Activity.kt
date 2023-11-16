package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.databinding.ActivityQuestionnaire6Binding

class Questionnaire6Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnNext.setOnClickListener {
            nextUI()

        }

    }

    fun nextUI() {

        val sharedPrefs = this.getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()){
            putString(Questionnaire1Activity.QuestionnaireConstants.question10 + "test", binding.sliderValue.value.toInt().toString())
            commit()
        }

        KickAshApp.globalUserData.questionnaire.stressfulMeter = binding.sliderValue.value.toInt()

        val i = Intent(this,Questionnaire7Activity::class.java)
        startActivity(i)
    }


}