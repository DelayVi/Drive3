package ru.delayvi.drive3.domain.entity

data class Dialog(
    val fromUser: Int,
    val toUser: Int,
    var messages: List<Message>
)
