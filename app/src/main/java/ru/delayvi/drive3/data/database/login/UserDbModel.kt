package ru.delayvi.drive3.data.database.login

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.entity.users.User
import java.util.*

@Entity(
    tableName = "all_registered_users",
    indices = [
        Index("username", unique = true)
    ]
)
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String,
) {
    fun toUser() = User(
        id = id,
        username = username
    )

    companion object {
        fun fromLoggedForm(loggedForm: LoggedForm) = with(loggedForm) {
            UserDbModel(
                id = 0, //autoGenerate
                username = login,
                password = password
            )
        }
    }
}