package com.irza.greenbox.feature.redeem

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.irza.greenbox.data.Repository
import com.irza.greenbox.model.reward.RewardModel
import com.irza.greenbox.model.user.UserModel

class RedeemViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val user = mutableStateOf<UserModel?>(null)
    val repository = Repository()
    val reward = mutableStateOf<RewardModel?>(null)

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

    fun getRewardById(id: String) {
        repository.getRewardById(id,
            onSuccess = {
                Log.d("DashboardViewModel", "onSuccess: $it")
                reward.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}