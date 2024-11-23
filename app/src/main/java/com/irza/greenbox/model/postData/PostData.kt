package com.irza.greenbox.model.postData

import androidx.annotation.DrawableRes

data class PostData(
    val judul: String,
    val category: String,
    @DrawableRes val imageRes: Int,
)