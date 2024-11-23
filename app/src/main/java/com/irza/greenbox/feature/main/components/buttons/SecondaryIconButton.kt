package com.irza.greenbox.feature.main.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.irza.greenbox.ui.theme.CustGray
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun SecondaryIconButton(text: String,onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(100))
            .background(color = CustGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                color = CustWhite
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Icon",
                tint = CustWhite,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}