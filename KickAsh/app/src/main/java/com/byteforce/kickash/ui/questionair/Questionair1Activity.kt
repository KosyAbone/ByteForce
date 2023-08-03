package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair1Binding

class Questionair1Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair1Binding.inflate(layoutInflater)
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

        binding.btnSkip.setOnClickListener {
            nextUI()
        }


    }

    fun nextUI() {
        val i = Intent(this@Questionair1Activity,Questionair2Activity::class.java)
        startActivity(i)
    }


}