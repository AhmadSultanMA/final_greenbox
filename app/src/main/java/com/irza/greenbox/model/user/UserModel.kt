package com.irza.greenbox.model.user

import androidx.annotation.DrawableRes

data class UserModel(
    val uid: String,
    val nama: String,
    val point: Long,
    val level: Long,
)