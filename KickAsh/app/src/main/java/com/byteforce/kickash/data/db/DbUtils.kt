package com.byteforce.kickash.data.db

import android.content.Context
import com.byteforce.kickash.KickAshApp
import com.byteforce.kickash.data.db.AppDatabase


object DbUtils {

    private var db = getDatabase()!!

   private fun getDatabase(): AppDatabase? {
        return KickAshApp.getDatabase()
    }

    fun seedTable(context: Context) {

//        val resource = context.resources.getIdentifier("movie", "raw", context.packageName)
//        val inputStream = context.resources.openRawResource(R.raw.movies)
//        val reader = InputStreamReader(inputStream)
//
//        val gson = Gson()
////        val movieData = gson.fromJson(reader,MovieModelItem::class.java)
//
//        val listType: Type = object : TypeToken<ArrayList<MovieModelItem?>?>() {}.type
//
//        val movieData: ArrayList<MovieModelItem> = gson.fromJson(reader, listType)
//
//
//
//        if (db!!.movieDao().getAllMovie().isEmpty())
//            db!!.movieDao().addAllMovie(movieData)


    }
//
//    fun getMovieRecords(context: Context): List<MovieModelItem> {
//        return db!!.movieDao().getAllMovie()
//    }
//
//    fun updateMovieRecord(movieModelItem: MovieModelItem) {
//        db.movieDao().updateMovie(movieModelItem)
//    }
//
//    fun deleteData(movieModel: MovieModelItem) {
//      //  db.movieDao().deleteMovie(movieModel.movieID)
//    }
//
//
//    fun addUser(userModelItem: UserModelItem) = db.userDao().addUser(userModelItem)

    fun getUser(userName:String, password:String) = db.userDao().getUser(userName,password)



}