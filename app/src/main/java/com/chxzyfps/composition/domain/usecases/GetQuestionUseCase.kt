package com.chxzyfps.composition.domain.usecases

import com.chxzyfps.composition.domain.entity.Question
import com.chxzyfps.composition.domain.repository.GameRepository

class GetQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, 6)
    }

    private companion object {

        private const val COUNT_OF_OPTIONS = 6

    }
}