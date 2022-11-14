package ru.delayvi.drive3.data.database.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.delayvi.drive3.domain.entity.users.LoggedForm

@Entity(tableName = "saved_user")
data class SavedUserDbModel(
    @PrimaryKey
    val localId: Int = 1,
    val login: String,
    val password: String
) {
    companion object {
        fun fromLoggedForm(loggedForm: LoggedForm) = with(loggedForm){
            SavedUserDbModel(
                login = login,
                password = password
            )
        }
    }
}
