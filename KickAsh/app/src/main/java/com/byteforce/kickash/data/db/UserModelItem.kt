package com.byteforce.kickash.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModelItem(
    @PrimaryKey(autoGenerate = true)
    var userId:Int = 0,
    var firstName:String = "",
    var lastName: String = "",
    var email:String = "",
    var userName:String = "",
    var password:String = ""
)

/**
 * UserID -> {
 *       firstName:String,
 *       lastName: String,
 *       email :String,
 *       username:String,
 *       gender :String
 *       Questionair -> {
 *              start_smoking: String
 *              no_of_cigartee_per_day :String,
 *              feel_smoking: String,
 *              trigger_smoking:String,
 *              stressful_meter:Int,
 *              prompt_decision:String,
 *              hobbies:[String],
 *       Smoking_History -> [ {
 *                id: Int,
 *                date_time: Long,
 *                is_relapsed: Boolean,
 *       }
 *
 * Articles -> [
 *          {
 *          id: Int,
 *          title: String,
 *          content:String,
 *          thumbnail_url:String,
 *          posted_by:String,
 *          posted_at: String,
 *          by_admin:Boolean

 * ]
 *
 *       ]
 *
 * }
 */