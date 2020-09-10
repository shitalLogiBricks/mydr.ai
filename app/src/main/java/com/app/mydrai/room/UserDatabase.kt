package com.app.mydrai.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mydrai.data.db.UserListEntity
import com.app.mydrai.room.dbHelper.DBUtils

@Database(entities = arrayOf(UserListEntity::class),
version = DBUtils.DB_VERSION, exportSchema = false
)
public abstract class DatabaseHelper : RoomDatabase() {

    abstract fun interfaceDao(): InterfaceDao


    companion object {
        private var INSTANCE: DatabaseHelper? = null
        fun getDatabase(context: Context): DatabaseHelper {
            if (INSTANCE == null) {
                synchronized(DatabaseHelper::class.java) {
                    if (INSTANCE == null) {
                        //Log.i(TAG, "getDatabase: ");
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseHelper::class.java, DBUtils.DATABASE_NAME
                        )
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this codelab.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            //.addCallback(callback)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }


}
