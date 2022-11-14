package ru.delayvi.drive3.data.database

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.delayvi.drive3.data.database.car.CarDao
import ru.delayvi.drive3.data.database.car.CarDbModel
import ru.delayvi.drive3.data.database.login.LoginDao
import ru.delayvi.drive3.data.database.login.SavedUserDbModel
import ru.delayvi.drive3.data.database.login.UserDbModel
import ru.delayvi.drive3.data.database.messenger.MessengerDao

@Database(entities = [CarDbModel::class, SavedUserDbModel::class, UserDbModel::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    abstract fun loginDao(): LoginDao

    abstract fun messengerDao(): MessengerDao

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