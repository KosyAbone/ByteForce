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
 *              start_smoking_date: String
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

data class FbUserData(
    val displayName: String = "",
    val email: String = "",
    val username: String = "",
    val gender: String = "",
    val questionnaire: FbQuestionnaire = FbQuestionnaire(),
    val smokingHistory: List<FbSmokingHistory> = listOf()
)

data class FbQuestionnaire(
    val startSmokingDate: String = "",
    val noOfCigarettePerDay: String = "",
    val feelSmoking: String = "",
    val triggerSmoking: String = "",
    val stressfulMeter: Int = 0,
    val promptDecision: String = "",
    val hobbies: List<String> = listOf()
)

data class FbSmokingHistory(
    val id: Int = 0,
    val dateTime: Long = 0L,
    val isRelapsed: Boolean = false
)