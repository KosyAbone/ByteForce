package com.byteforce.kickash.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.byteforce.kickash.R
import com.byteforce.kickash.ui.base.BaseActivity

class SlideShowActivity: BaseActivity() {

    val list = listOf(
        R.drawable.a1emaillogina,
        R.drawable.a2registera,
        R.drawable.a3forgotpasswordsendcodea,
        R.drawable.a4forgotpasswordentercodea,
        R.drawable.a5forgotpasswordpasswordreseta,
        R.drawable.a6forgotpasswordpasswordresetconfirmationa,
        R.drawable.a7questionnairelaunchscreena,
        R.drawable.a8questionnaire2a,
        R.drawable.a9questionnaire1a,
        R.drawable.a10questionnaire3a,
        R.drawable.a11questionnaire4a,
        R.drawable.a12questionnaire5a,
        R.drawable.a13questionnaire6a,
        R.drawable.a14questionnaire7a,
        R.drawable.a15questionnaire8a,
        R.drawable.a16questionnaireenda,
        R.drawable.a17homescreensubasha,
        R.drawable.a18homescreensubash1a,
        R.drawable.a19communityscreena,
        R.drawable.a20rewardscreena,
        R.drawable.a21progresssuraja,
        R.drawable.a22historytabgautama,
        R.drawable.a23articlesscreena,
        R.drawable.a24articlesscreendetaila,
        R.drawable.a25profilescreena,
        R.drawable.a26socialscreen1a,
        R.drawable.a27socialscreen2a,
        R.drawable.a28socialscreen3a,
    )

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