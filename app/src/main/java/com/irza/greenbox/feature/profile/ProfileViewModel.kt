package com.irza.greenbox.feature.profile

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.post.PostModel
import com.irza.greenbox.model.user.UserModel

class ProfileViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val user = mutableStateOf<UserModel?>(null)
    val repository = Repository()
    val isSuccess = mutableStateOf(false)

    fun logout() {
        repository.logout(
            onSuccess = {
                isSuccess.value = true
                Log.e("Berhasil", "Logout Berhasil")
            },
            onFailed = {
                isSuccess.value = false
                Log.e("Gagal", it.toString())
            }
        )
    }

    init {
        repository.getUser(
            auth.uid ?: "",
            onSuccess = {
                Log.e("Berhasil", it.toString())
                user.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}