package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair10Binding
import com.byteforce.kickash.databinding.ActivityQuestionair8Binding

class Questionair10Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair10Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair10Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnSkip.setOnClickListener {
            nextUI()
        }

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
        val i = Intent(this,Questionair9Activity::class.java)
        startActivity(i)
    }


}