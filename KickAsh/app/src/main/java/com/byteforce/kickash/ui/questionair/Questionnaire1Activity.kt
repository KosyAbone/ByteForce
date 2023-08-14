package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.databinding.ActivityQuestionnaire1Binding

class Questionnaire1Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire1Binding.inflate(layoutInflater)
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
            putString(QuestionnaireConstants.question1 + username, answer)
            commit()
        }

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