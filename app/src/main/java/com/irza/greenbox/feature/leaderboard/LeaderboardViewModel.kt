package com.irza.greenbox.feature.leaderboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.user.UserModel

class LeaderboardViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)
    val leaderboard = mutableStateListOf<UserModel>()

    fun getAllUserByPoint() {
        repository.getAllUserByPoint(
            onSuccess = {
                leaderboard.clear()
                leaderboard.addAll(it)
            },
            onFailed = {
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