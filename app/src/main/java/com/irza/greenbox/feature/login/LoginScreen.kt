package com.irza.greenbox.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.buttons.PrimaryButton
import com.irza.greenbox.feature.main.components.textFields.EmailField
import com.irza.greenbox.feature.main.components.textFields.PasswordField
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00A36C)), // Warna hijau
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Icon(
            painter = painterResource(id = R.drawable.whitelogo), // Ganti dengan resource logo Anda
            contentDescription = "App Logo",
            tint = Color.White,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(color = CustWhite)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Email Address", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                EmailField(value = email.value, onValueChange = { email.value = it })

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Password", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                PasswordField(value = password.value, onValueChange = { password.value = it })

                // Forgot Password
                Text(
                    text = "Forgot password?",
                    color = CustBlack,
                    modifier = Modifier
                        .align(Alignment.End),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                PrimaryButton(text = "Log In") {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Or",
                    modifier = Modifier.fillMaxWidth(),
                    color = CustBlack,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Google Button
                OutlinedButton(
                    onClick = { /* Handle Google Login */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Continue with Google", color = CustBlack, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign Up
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Don’t have an account?", color = CustBlack, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Sign Up",
                        color = CustGreen,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.SignUp.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
