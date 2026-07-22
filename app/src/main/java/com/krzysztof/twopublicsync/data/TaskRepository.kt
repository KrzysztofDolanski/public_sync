package com.krzysztof.twopublicsync.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepository {

    fun waitForPartner(): Flow<Boolean> = flow {
        delay(3000)
        emit(true)
    }

    suspend fun confirmGesture() {
        delay(1000)
    }
}
