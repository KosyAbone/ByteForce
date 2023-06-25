package com.byteforce.kickash.ui.questionair

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.MainActivity
import com.byteforce.kickash.R
import com.byteforce.kickash.databinding.ActivityQuestionair1Binding
import com.byteforce.kickash.databinding.ActivityQuestionair9Binding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class Questionair9Activity: AppCompatActivity() {


    private lateinit var binding: ActivityQuestionair9Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionair9Binding.inflate(layoutInflater)
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
        binding.btnSkip.setOnClickListener {
            nextUI()

        }




    }

    fun nextUI() {
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }


}