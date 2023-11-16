package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.data.db.FbUserData
import com.byteforce.kickash.databinding.ActivityQuestionnaireEndBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class QuestionnaireEndActivity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionnaireEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    fun initUI() {


        //https://github.com/DanielMartinus/Konfetti

        val party =  Party(speed = 0f,
            maxSpeed = 30f, damping = 0.9f, spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 100,
                TimeUnit.MILLISECONDS).max(100))

        binding.konfettiView.start(party)


        saveFirstTimeUser(Firebase.auth.currentUser!!.uid, KickAshApp.globalUserData)


    }

    private fun saveFirstTimeUser(user: String, userData: FbUserData) {
        val db = FirebaseFirestore.getInstance()
        val userDocumentRef = db.collection("users").document(user)

        // Create a map to represent the user data
        val userMap = hashMapOf(
            "displayName" to userData.displayName,
            "email" to userData.email,
            "username" to userData.username,
            "gender" to userData.gender,
            "questionnaire" to hashMapOf(
                "start_smoking_date" to userData.questionnaire.startSmokingDate,
                "no_of_cigarette_per_day" to userData.questionnaire.noOfCigarettePerDay,
                "feel_smoking" to userData.questionnaire.feelSmoking,
                "trigger_smoking" to userData.questionnaire.triggerSmoking,
                "stressful_meter" to userData.questionnaire.stressfulMeter,
                "prompt_decision" to userData.questionnaire.promptDecision,
                "hobbies" to userData.questionnaire.hobbies
            ),
            "smokingHistory" to userData.smokingHistory.map {
                hashMapOf(
                    "id" to it.id,
                    "date_time" to it.dateTime,
                    "is_relapsed" to it.isRelapsed
                )
            }
        )

        KickAshApp.globalUserData = userData

        // Save the user data to Firestore
        userDocumentRef.set(userData)
            .addOnSuccessListener {
                // Data saved successfully

                binding.btnNext.setOnClickListener {
                    nextUI()

                }


            }
            .addOnFailureListener { e ->

                e.printStackTrace()
                Toast.makeText(this,"Cannot save questionair data. Try again later..", Toast.LENGTH_SHORT).show()
                // Handle the failure
            }
    }


    fun nextUI() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


}