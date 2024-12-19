package com.irza.greenbox.feature.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel: ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}