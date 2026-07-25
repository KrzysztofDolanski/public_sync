package com.krzysztof.twopublicsync.score

class ScoreRepository {

    private var score = 0

    fun getScore(): Int = score

    fun addPoint() {
        score++
    }
}
