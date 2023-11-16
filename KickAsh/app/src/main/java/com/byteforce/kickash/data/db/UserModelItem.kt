package com.byteforce.kickash.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName

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
    @PropertyName("displayName")
    val displayName: String = "",
    @PropertyName("email")
    val email: String = "",
    @PropertyName("username")
    val username: String = "",
    @PropertyName("gender")
    var gender: String = "",
    @PropertyName("questionnaire")
    val questionnaire: FbQuestionnaire = FbQuestionnaire(),
    @PropertyName("smokingHistory")
    val smokingHistory: List<FbSmokingHistory> = listOf()
)

data class FbQuestionnaire(

    @PropertyName("start_smoking_date")
    var startSmokingDate: String = "",
    @PropertyName("no_of_cigarette_per_day")
    var noOfCigarettePerDay: String = "",
    @PropertyName("feel_smoking")
    var feelSmoking: String = "",
    @PropertyName("trigger_smoking")
    var triggerSmoking: String = "",
    @PropertyName("stressful_meter")
    var stressfulMeter: Int = 0,
    @PropertyName("prompt_decision")
    var promptDecision: String = "",
    @PropertyName("hobbies")
    var hobbies: List<String> = listOf()
)

data class FbSmokingHistory(
    @PropertyName("id")
    val id: Int = 0,
    @PropertyName("date_time")
    val dateTime: Long = 0L,
    @PropertyName("is_relapsed")
    val isRelapsed: Boolean = false
)