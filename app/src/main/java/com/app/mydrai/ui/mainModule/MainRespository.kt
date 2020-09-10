package com.app.mydrai.ui.mainModule

import android.app.Application

import com.app.mydrai.room.DatabaseHelper


class MainRespository{


    companion object {

        var mainRespository: MainRespository? = null
        var mContext: Application? = null
        var databaseHelper: DatabaseHelper? = null

        @Synchronized
        @JvmStatic
        fun getInstance(context: Application): MainRespository {
            mContext = context
            databaseHelper = DatabaseHelper.getDatabase(mContext!!)
            if (mainRespository == null) mainRespository = MainRespository()
            return mainRespository!!
        }


    }
}
