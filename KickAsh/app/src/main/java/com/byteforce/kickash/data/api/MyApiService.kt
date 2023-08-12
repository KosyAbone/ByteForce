package com.byteforce.kickash.data.api



import com.byteforce.kickash.ui.main.articles.ArticleModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun register(@Body registrationData: RegistrationData): Call<RegistrationResponse>

    @POST("auth/login")
    fun login(@Body loginData: LoginData): Call<Map<String, String>>

    @GET("articles")
    fun getArticles(): Call<List<ArticleModel>>


    @GET("profile")
    fun getUserDetail(@Header("Auth") token:String): Call<UserDetail>

}