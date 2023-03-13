package com.chxzyfps.composition.domain.repository

import com.chxzyfps.composition.domain.entity.GameSettings
import com.chxzyfps.composition.domain.entity.Level
import com.chxzyfps.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSum: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}