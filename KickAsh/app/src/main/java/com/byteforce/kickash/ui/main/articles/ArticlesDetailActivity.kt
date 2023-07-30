package com.byteforce.kickash.ui.main.articles

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.squareup.picasso.Picasso

class ArticlesDetailActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_detail)

        Picasso.get()
            .load("https://images.unsplash.com/photo-1527856263669-12c3a0af2aa6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80")
            .into(findViewById<ImageView>
            (R.id.article_header_image))


        val articleDetail = """
            Smoking is a habit that has been ingrained in our society for decades, but its detrimental effects on our health cannot be ignored. Whether you are a long-time smoker or just starting, the decision to quit smoking is one of the most significant choices you can make for your well-being. In just 50 lines, lets explore the importance of quitting smoking and the transformative impact it can have on your life.

                Smoking is a leading cause of preventable diseases, including lung cancer, heart disease, and respiratory illnesses. Quitting can significantly reduce these risks.

                Your journey to quit smoking may not be easy, but remember that perseverance and determination will lead you to success.

                Create a support system of friends, family, or join a quit smoking group to stay motivated and accountable.

                Understand your triggers and find healthier alternatives to cope with stress, such as exercise, meditation, or hobbies.

                As you quit smoking, your sense of taste and smell will improve, making food more enjoyable.

                The financial benefits of quitting are immense, as you'll save money on cigarettes and healthcare costs.</string>

        """.trimIndent()

        findViewById<TextView>(R.id.article_content).text = articleDetail

    }
}