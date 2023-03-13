package com.chxzyfps.composition.domain.usecases

import com.chxzyfps.composition.domain.entity.GameSettings
import com.chxzyfps.composition.domain.entity.Level
import com.chxzyfps.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}