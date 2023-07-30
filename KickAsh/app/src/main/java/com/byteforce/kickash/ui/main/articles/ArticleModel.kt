package com.byteforce.kickash.ui.main.articles

data class ArticleModel(
    val id:Int,
    val title:String,
    val content:String,
    val datePosted:String,
    val postedBy:String,
    var imageUrl:String = ""
    )