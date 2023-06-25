package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair2Binding

class Questionair2Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnOption1.setOnClickListener {
            nextUI()

        }

        binding.btnOption2.setOnClickListener {
            nextUI()

        }

        binding.btnOption3.setOnClickListener {

            nextUI()
        }


    }

    fun nextUI() {
        val i = Intent(this,Questionair3Activity::class.java)
        startActivity(i)
    }


}