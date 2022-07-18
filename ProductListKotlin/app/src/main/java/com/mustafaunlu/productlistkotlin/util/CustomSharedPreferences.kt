package com.mustafaunlu.productlistkotlin.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class CustomSharedPreferences {
    private var PREFERENCES_TIME="preferences_time"


    companion object{

        private var sharedPreferences : SharedPreferences?=null

        @Volatile
        private var instance: CustomSharedPreferences?=null
        private var lock=Any()

        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock){
            instance ?: makeCustomSharedPreferences(context).also {
                instance=it
            }
        }



        private fun makeCustomSharedPreferences(context : Context) : CustomSharedPreferences{
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }
    fun saveTime(time : Long){
        sharedPreferences?.edit()?.putLong(PREFERENCES_TIME,time)?.commit()
    }
    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)


}