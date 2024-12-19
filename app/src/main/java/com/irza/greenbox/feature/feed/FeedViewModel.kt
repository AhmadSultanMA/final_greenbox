package com.irza.greenbox.feature.feed

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.post.PostModel
import com.irza.greenbox.model.user.UserModel

class FeedViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)
    val postData = mutableStateListOf<PostModel>()

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