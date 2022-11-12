package ru.delayvi.drive3.data.database

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.delayvi.drive3.data.database.car.CarDao
import ru.delayvi.drive3.data.database.car.CarDbModel
import ru.delayvi.drive3.domain.entity.Car
import ru.delayvi.drive3.domain.entity.Color
import ru.delayvi.drive3.domain.entity.Fuel

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