package ru.delayvi.drive3.data.database.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.User

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun userSignUp(user: UserDbModel)

    @Query("SELECT * FROM all_registered_users WHERE username =:username AND password =:password LIMIT 1")
    suspend fun userSignIn(username: String, password: String): UserDbModel

    @Query("SELECT * FROM saved_user WHERE localId = 1 LIMIT 1")
    suspend fun getUserFromData():SavedUserDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocalUser(user: SavedUserDbModel)

    @Query("DELETE FROM saved_user WHERE localId = 1")
    suspend fun logout()

}