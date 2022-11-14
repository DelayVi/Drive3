package ru.delayvi.drive3.domain.usecases.users

import ru.delayvi.drive3.domain.repository.UserRepository
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.isAuthorized()
}