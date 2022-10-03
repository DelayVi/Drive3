package ru.delayvi.drive3.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db

            }

        }
    }
}