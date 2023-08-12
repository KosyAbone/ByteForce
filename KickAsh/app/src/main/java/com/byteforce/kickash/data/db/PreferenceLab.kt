package com.byteforce.kickash.data.db

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * usage:
 * PreferenceLab(this).token = "SXXXX" // set token
 *val token = PreferenceLab(this).token // get token
 */
class PreferenceLab(val context: Context) {

    private val mSharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    var toke: String
        get() = mSharedPreferences.getString(TOKEN, "")!!
        set(b) {
            mSharedPreferences.edit().putString(TOKEN, b).apply()
        }

    var isRegistered: Boolean
        get() = mSharedPreferences.getBoolean(IS_REGISTERED, false)
        set(b) {
            mSharedPreferences.edit().putBoolean(IS_REGISTERED, b).apply()
        }

    var questionairStatus: Boolean
        get() = mSharedPreferences.getBoolean(QUESTIONAIR_STATUS, false)
        set(b) {
            mSharedPreferences.edit().putBoolean(QUESTIONAIR_STATUS, b).apply()
        }


    var firstTime: Boolean
        get() = mSharedPreferences.getBoolean(FIRST_TIME, true)
        set(b) {
            mSharedPreferences.edit().putBoolean(FIRST_TIME, b).apply()
        }

    var lastUpdatedTime: Long
        get() = mSharedPreferences.getLong(LAST_UPDATED, 0L)
        set(b) {
            mSharedPreferences.edit().putLong(LAST_UPDATED, b).apply()
        }


    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var sPreferenceLab: PreferenceLab? = null
        private const val TOKEN = "firebase_instance_token"
        private const val IS_REGISTERED = "is_registered"
        private const val FIRST_TIME = "first_time"
        private const val QUESTIONAIR_STATUS = "voting_status"
        private const val LAST_UPDATED = "last_updated"

        @Deprecated("Use DI for getting the instance")
        operator fun get(context: Context): PreferenceLab {
            if (sPreferenceLab == null) {
                sPreferenceLab = PreferenceLab(context.applicationContext)
            }
            return sPreferenceLab as PreferenceLab
        }
    }
}

