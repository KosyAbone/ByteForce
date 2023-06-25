package com.byteforce.kickash.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.byteforce.kickash.R
import com.byteforce.kickash.ui.base.BaseActivity

class SlideShowActivity: BaseActivity() {

    val list = listOf<Int>()

    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_slide)


        val forwardButton = findViewById<View>(R.id.vForward)
        val backButton = findViewById<View>(R.id.vBack)

        val image = findViewById<ImageView>(R.id.imageView)

        image.setImageResource(list[currentIndex])

        forwardButton.setOnClickListener {
            currentIndex++
            image.setImageResource(list[currentIndex])
        }

        backButton.setOnClickListener {
            currentIndex--
            image.setImageResource(list[currentIndex])
        }









    }





}