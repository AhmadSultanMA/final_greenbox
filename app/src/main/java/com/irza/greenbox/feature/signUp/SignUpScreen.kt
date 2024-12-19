package com.irza.greenbox.feature.signUp

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.buttons.PrimaryButton
import com.irza.greenbox.feature.main.components.textFields.EmailField
import com.irza.greenbox.feature.main.components.textFields.NameField
import com.irza.greenbox.feature.main.components.textFields.PasswordField
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(navController: NavController) {
    val nama = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val viewModel : SignUpViewModel = viewModel()

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(3000)
            viewModel.errMsg.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isSuccess.value){
        if (viewModel.isSuccess.value) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.SignUp.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00A36C)), // Warna hijau
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Icon(
            painter = painterResource(id = R.drawable.whitelogo), // Ganti dengan resource logo Anda
            contentDescription = "App Logo",
            tint = Color.White,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(color = CustWhite)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Full Name", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
                NameField(value = nama.value, onValueChange = { nama.value = it })

                Spacer(modifier = Modifier.height(16.dp))

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

                if (viewModel.isLoading.value){
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }else {
                    PrimaryButton(text = "Sign Up") {
                        if(email.value == "" || password.value == "" || nama.value == ""){
                            viewModel.errMsg.value = "Harap isi semua kolom"
                        }else{
                            viewModel.signUp(email.value, password.value, nama.value)
                        }
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
                    Text("Already have an account?", color = CustBlack, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Log In",
                        color = CustGreen,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.SignUp.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}