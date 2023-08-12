package com.byteforce.kickash.ui.main.articles

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso

class ArticlesDetailActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_detail)

        val data = intent.getSerializableExtra("data") as ArticleModel


        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            onBackPressed()
        }

        Picasso.get()
            .load(data.imageUrl)
            .into(findViewById<ImageView>
                (R.id.article_header_image))


        val articleDetail =data.content.trimIndent()

        findViewById<TextView>(R.id.article_content).text = articleDetail
        findViewById<TextView>(R.id.article_title).text= data.title
        findViewById<TextView>(R.id.article_author).text = "By: Admin · ${data.postedBy}· ${data.datePosted}"


    }
}