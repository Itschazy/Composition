package com.chxzyfps.composition.domain.entity

data class Question(
    val sum: Int,
    val visibleNumver: Int,
    val options: List<Int>
) {
    val rightAnswer: Int
        get() = sum - visibleNumver
}