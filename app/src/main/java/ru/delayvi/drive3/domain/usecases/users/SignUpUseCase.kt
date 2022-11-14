package ru.delayvi.drive3.domain.usecases.users

import ru.delayvi.drive3.domain.entity.users.LoggedForm
import ru.delayvi.drive3.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.math.log

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(loggedForm: LoggedForm) = userRepository.signUp(loggedForm)
}