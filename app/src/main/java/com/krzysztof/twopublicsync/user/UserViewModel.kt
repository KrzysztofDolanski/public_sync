package com.krzysztof.twopublicsync.user

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    private val _avatar = MutableStateFlow(repository.getAvatar())
    val avatar: StateFlow<Int> = _avatar

    private val _name = MutableStateFlow(repository.getUserName())
    val name: StateFlow<String> = _name

    fun changeAvatar(@DrawableRes avatarRes: Int) {
        viewModelScope.launch {
            repository.setAvatar(avatarRes)
            _avatar.value = avatarRes
        }
    }

    fun changeName(newName: String) {
        viewModelScope.launch {
            repository.setUserName(newName)
            _name.value = newName
        }
    }
}
