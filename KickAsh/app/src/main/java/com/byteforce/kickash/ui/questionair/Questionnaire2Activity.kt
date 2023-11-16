package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.databinding.ActivityQuestionnaire2Binding

class Questionnaire2Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnOption1.setOnClickListener {
            var buttonText = binding.btnOption1.text.toString()
            nextUI(buttonText)
        }

        binding.btnOption2.setOnClickListener {
            var buttonText = binding.btnOption2.text.toString()
            nextUI(buttonText)
        }

        binding.btnOption3.setOnClickListener {
            var buttonText = binding.btnOption3.text.toString()
            nextUI(buttonText)
        }
    }

    fun nextUI(answer : String) {
        var username = "test"

        val sharedPrefs = this.getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()){
            putString(Questionnaire1Activity.QuestionnaireConstants.question2 + username, answer)
            commit()
        }

        KickAshApp.globalUserData.questionnaire.noOfCigarettePerDay = answer

        val i = Intent(this,Questionnaire3Activity::class.java)
        startActivity(i)
    }


}