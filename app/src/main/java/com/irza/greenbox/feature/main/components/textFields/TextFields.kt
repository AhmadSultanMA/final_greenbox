package com.irza.greenbox.feature.main.components.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.irza.greenbox.ui.theme.CustDarkGrayField
import com.irza.greenbox.ui.theme.CustGrayField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(CustGrayField, shape = RoundedCornerShape(100))
            .height(55.dp),

        value = value,
        shape = RoundedCornerShape(100),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Masukkan nama lengkap anda", color = CustDarkGrayField, style = MaterialTheme.typography.bodySmall) },
        textStyle = TextStyle(color = CustDarkGrayField),
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(CustGrayField, shape = RoundedCornerShape(100))
            .height(55.dp),

        value = value,
        shape = RoundedCornerShape(100),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Masukkan alamat email anda", color = CustDarkGrayField, style = MaterialTheme.typography.bodySmall) },
        textStyle = TextStyle(color = CustDarkGrayField),
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .background(CustGrayField, shape = RoundedCornerShape(100))
            .height(55.dp),

        value = value,
        shape = RoundedCornerShape(100),
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Masukkan kata sandi anda", color = CustDarkGrayField, style = MaterialTheme.typography.bodySmall) },
        textStyle = TextStyle(color = CustDarkGrayField),
        singleLine = true,
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                val image = if (isPasswordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                Icon(imageVector = image, contentDescription = "Toggle password visibility", tint = CustDarkGrayField)
            }
        }
    )
}