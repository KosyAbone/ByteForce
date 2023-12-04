package com.byteforce.kickash.ui.main.articles

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byteforce.kickash.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso


class ArticlesDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_detail)

        val data = intent.getSerializableExtra("data") as ArticleModel


        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            onBackPressed()
        }

        if(data.imageUrl.endsWith(".mp4")) {

            loadPlayer(data.imageUrl)
            findViewById<ImageView>(R.id.iv_image).visibility = View.INVISIBLE

        } else {
            findViewById<ImageView>(R.id.iv_image).visibility = View.VISIBLE

            Picasso.get()
                .load(data.imageUrl)
                .into(findViewById<ImageView>
                    (R.id.iv_image))
        }



        val articleDetail = data.content.trimIndent()

        findViewById<TextView>(R.id.article_content).text = articleDetail
        findViewById<TextView>(R.id.article_title).text = data.title
        findViewById<TextView>(R.id.article_author).text =
            "By: Admin · ${data.postedBy}· ${data.datePosted}"


    }

    var player: ExoPlayer? = null

    var playerView: StyledPlayerView? = null

    fun loadPlayer(url: String) {


        Toast.makeText(this,"Loading video",Toast.LENGTH_LONG).show()

//        player = SimpleExoPlayer.Builder(this).build()
////        player = ExoPlayer.Builder(this).build()
//
        playerView = findViewById(R.id.playerView)
//
//        playerView?.player = player
//
//        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
//        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
//
//
//        // Prepare the player with the media source
//        player?.setMediaSource(mediaSource)
//        player?.prepare()
//
//        player?.playWhenReady = true // Auto play

        player = ExoPlayer.Builder(this@ArticlesDetailActivity).build()
        playerView!!.player = player
        val mediaItem: MediaItem = MediaItem.fromUri("https://video.blender.org/download/videos/3d95fb3d-c866-42c8-9db1-fe82f48ccb95-804.mp4")
        player!!.setMediaItem(mediaItem)
        player!!.prepare()
        player!!.playWhenReady = true

    }

    override fun onStop() {
        super.onStop()
        player?.setPlayWhenReady(false);
        player?.release();
        player = null;    }
}