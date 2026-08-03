package com.krzysztof.twopublicsync.user

import androidx.annotation.DrawableRes
import com.krzysztof.twopublicsync.R

class UserRepository {

    private var userName: String = "Użytkownik"

    @DrawableRes
    private var avatarRes: Int = R.drawable.ic_launcher_foreground

    fun getUserName(): String = userName

    fun setUserName(name: String) {
        userName = name
    }

    @DrawableRes
    fun getAvatar(): Int = avatarRes

    fun setAvatar(@DrawableRes avatar: Int) {
        avatarRes = avatar
    }
}
