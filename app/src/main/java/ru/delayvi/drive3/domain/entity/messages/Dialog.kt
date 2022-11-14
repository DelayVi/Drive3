package ru.delayvi.drive3.domain.entity.messages

data class Dialog(
    val fromUser: Int,
    val toUser: Int,
    var messages: List<Message>
)
