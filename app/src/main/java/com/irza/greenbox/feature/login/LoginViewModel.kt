package com.irza.greenbox.feature.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.irza.greenbox.data.Repository

class LoginViewModel: ViewModel() {
    val repository = Repository()
    val isLogin = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")

    fun signIn(
        email : String,
        password : String,
    ){
        isLoading.value = true
        repository.login(
            email,
            password,
            onSuccess = {
                isLoading.value = false
                isLogin.value = it
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = it.toString()
            }
        )
    }
}