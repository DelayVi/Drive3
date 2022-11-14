package ru.delayvi.drive3.data.database.login

data class UserFromDbModel(
    val id:Int,
    val username:String,
    val favoriteCars: MutableList<Int>
)
