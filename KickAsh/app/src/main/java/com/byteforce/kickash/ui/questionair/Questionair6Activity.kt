package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair6Binding

class Questionair6Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

        binding.btnSkip.setOnClickListener {
            nextUI()

        }



    }

    fun nextUI() {
        val i = Intent(this,Questionair7Activity::class.java)
        startActivity(i)
    }


}