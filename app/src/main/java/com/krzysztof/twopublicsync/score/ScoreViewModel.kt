package com.krzysztof.twopublicsync.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScoreViewModel : ViewModel() {

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    fun addPoint() {
        viewModelScope.launch {
            _score.value = _score.value + 1
        }
    }
}
