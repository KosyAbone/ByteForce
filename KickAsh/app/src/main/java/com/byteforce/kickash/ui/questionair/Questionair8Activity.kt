package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair8Binding

class Questionair8Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair8Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnSkip.setOnClickListener {
            nextUI()
        }



    }

    fun nextUI() {
        val i = Intent(this,Questionair9Activity::class.java)
        startActivity(i)
    }


}