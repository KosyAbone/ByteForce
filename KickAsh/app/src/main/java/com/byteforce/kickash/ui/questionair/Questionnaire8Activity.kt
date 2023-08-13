package com.byteforce.kickash.ui.questionair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.byteforce.kickash.databinding.ActivityQuestionnaire8Binding

class Questionnaire8Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaire8Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaire8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {
        binding.otherInputField.isEnabled = binding.btnOption8.isChecked

        if(binding.btnOption1.isChecked || binding.btnOption2.isChecked || binding.btnOption3.isChecked ||
            binding.btnOption4.isChecked || binding.btnOption5.isChecked || binding.btnOption6.isChecked ||
            binding.btnOption7.isChecked || (binding.btnOption8.isChecked && (binding.otherInputField.text!!.length > 0)) ){
            binding.btnNext.isEnabled = true
        }

        binding.btnOption1.setOnClickListener {
            if(binding.btnOption1.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption2.isChecked || binding.btnOption3.isChecked ||
                binding.btnOption4.isChecked || binding.btnOption5.isChecked || binding.btnOption6.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
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
                binding.btnOption4.isChecked || binding.btnOption5.isChecked || binding.btnOption6.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
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
                binding.btnOption4.isChecked || binding.btnOption5.isChecked || binding.btnOption6.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
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
                binding.btnOption3.isChecked || binding.btnOption5.isChecked || binding.btnOption6.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
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
                binding.btnOption3.isChecked || binding.btnOption4.isChecked || binding.btnOption6.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption6.setOnClickListener {
            if(binding.btnOption6.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption4.isChecked || binding.btnOption5.isChecked ||
                binding.btnOption7.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption7.setOnClickListener {
            if(binding.btnOption6.isChecked){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption4.isChecked || binding.btnOption5.isChecked ||
                binding.btnOption6.isChecked || (binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!)){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.btnOption8.setOnClickListener {
            binding.otherInputField.isEnabled = binding.btnOption8.isChecked
            binding.otherInputField.text?.clear()

            if(binding.btnOption8.isChecked && !binding.otherInputField.text?.isEmpty()!!){
                binding.btnNext.isEnabled = true
            }
            else if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption4.isChecked || binding.btnOption5.isChecked ||
                binding.btnOption6.isChecked || binding.btnOption7.isChecked){
                binding.btnNext.isEnabled = true
            }
            else {
                binding.btnNext.isEnabled = false
            }
        }

        binding.otherInputField.addTextChangedListener {
            binding.btnNext.isEnabled = binding.otherInputField.text?.isNotEmpty()!!

            if (binding.btnOption1.isChecked || binding.btnOption2.isChecked ||
                binding.btnOption3.isChecked || binding.btnOption4.isChecked || binding.btnOption5.isChecked ||
                binding.btnOption6.isChecked || binding.btnOption7.isChecked && !binding.btnOption8.isChecked){
                binding.btnNext.isEnabled = true
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
            if(binding.btnOption6.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption6.text.toString()
                }
                else{
                    answer += "," + binding.btnOption6.text.toString()
                }
            }
            if(binding.btnOption7.isChecked){
                if(answer.isEmpty()){
                    answer = binding.btnOption7.text.toString()
                }
                else{
                    answer += "," + binding.btnOption7.text.toString()
                }
            }
            if(binding.btnOption8.isChecked && binding.otherInputField.text?.isNotEmpty()!!){
                if(answer.isEmpty()){
                    answer = binding.otherInputField.text.toString()
                }
                else{
                    answer += "," + binding.otherInputField.text.toString()
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
            putString(Questionnaire1Activity.QuestionnaireConstants.question8 + username, answer)
            commit()
        }

        val i = Intent(this,Questionnaire9Activity::class.java)
        startActivity(i)
    }


}