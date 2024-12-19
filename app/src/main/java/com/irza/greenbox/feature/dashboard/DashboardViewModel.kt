package com.irza.greenbox.feature.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.post.PostModel
import com.irza.greenbox.model.user.UserModel

class DashboardViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val user = mutableStateOf<UserModel?>(null)
    val repository = Repository()
    val postData = mutableStateListOf<PostModel>()
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

        repository.getAllPost(
            onSuccess = {
                Log.d("DashboardViewModel", "onSuccess: $it")
                postData.clear()
                postData.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}