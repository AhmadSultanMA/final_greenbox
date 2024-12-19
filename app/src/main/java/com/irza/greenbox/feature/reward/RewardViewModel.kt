package com.irza.greenbox.feature.reward

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.reward.RewardModel
import com.irza.greenbox.model.user.UserModel

class RewardViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)
    val reward = mutableStateListOf<RewardModel>()


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

        repository.getAllReward(
            onSuccess = {
                Log.d("RewardViewModel", "onSuccess: $it")
                reward.clear()
                reward.addAll(it)
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}