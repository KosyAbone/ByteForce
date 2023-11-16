package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.databinding.ActivityQuestionnaire7Binding

class Questionnaire7Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {

//        if(binding.btnOption1.isChecked || binding.btnOption2.isChecked || binding.btnOption3.isChecked ||
//            binding.btnOption4.isChecked || binding.btnOption5.isChecked){
//            binding.btnNext.isEnabled = true
//        }

        binding.btnOption1.setOnClickListener {
            if(binding.btnOption1.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption2.isChecked || binding.btnOption3.isChecked ||
                binding.btnOption4.isChecked || binding.btnOption5.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption2.setOnClickListener {
            if(binding.btnOption2.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption3.isChecked ||
                binding.btnOption4.isChecked || binding.btnOption5.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption3.setOnClickListener {
            if(binding.btnOption3.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption4.isChecked || binding.btnOption5.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption4.setOnClickListener {
            if(binding.btnOption4.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption5.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption5.setOnClickListener {
            if(binding.btnOption5.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption4.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnNext.setOnClickListener {
            var answer : String = ""

            if(binding.btnOption1.isChecked){
                answer = binding.btnOption1.text.toString()
            }
            if(binding.btnOption2.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption2.text.toString()
                }
                else{
                    answer += "," + binding.btnOption2.text.toString()
                }
            }
            if(binding.btnOption3.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption3.text.toString()
                }
                else{
                    answer += "," + binding.btnOption3.text.toString()
                }
            }
            if(binding.btnOption4.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption4.text.toString()
                }
                else{
                    answer += "," + binding.btnOption4.text.toString()
                }
            }
            if(binding.btnOption5.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption5.text.toString()
                }
                else{
                    answer += "," + binding.btnOption5.text.toString()
                }
            }
            nextUI(answer)
        }
    }

    fun nextUI(answer : String) {
        var username = "test"

        val sharedPrefs = this.getSharedPreferences(
            "QuestionnaireData", Context.MODE_PRIVATE)
        with(sharedPrefs.edit()){
            putString(Questionnaire1Activity.QuestionnaireConstants.question7 + username, answer)
            commit()
        }

        KickAshApp.globalUserData.questionnaire.promptDecision = answer


        val i = Intent(this,Questionnaire8Activity::class.java)
        startActivity(i)
    }


}