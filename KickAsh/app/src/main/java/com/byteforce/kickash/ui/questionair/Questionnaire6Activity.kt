package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        val i = Intent(this,Questionnaire7Activity::class.java)
        startActivity(i)
    }


}