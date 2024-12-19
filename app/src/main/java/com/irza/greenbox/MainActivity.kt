package com.irza.greenbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.irza.greenbox.feature.main.navigation.Navigation
import com.irza.greenbox.ui.theme.GreenBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this)

        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        firebaseAppCheck.getAppCheckToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result?.token
                    // Use the token if needed (e.g., for Firebase requests)
                    println("App Check Token: $token")
                } else {
                    println("Error: ${task.exception?.message}")
                }
            }

        setContent {
            GreenBoxTheme {
                Navigation()
            }
        }
    }
}
