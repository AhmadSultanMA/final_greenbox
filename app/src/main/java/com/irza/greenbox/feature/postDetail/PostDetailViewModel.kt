package com.irza.greenbox.feature.postDetail

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.post.PostModel

class PostDetailViewModel: ViewModel() {
    val repository = Repository()
    val post = mutableStateOf<PostModel?>(null)

    fun getPostById(id: String) {
        repository.getPostById(id,
            onSuccess = {
                Log.d("DashboardViewModel", "onSuccess: $it")
                post.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}