package com.irza.greenbox.feature.main.components.indicator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.irza.greenbox.ui.theme.CustBeige
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun LinearIndicator(percent : Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        LinearProgressIndicator(
            progress = { percent / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(shape = RoundedCornerShape(100)),
            color = CustBeige,
            trackColor = CustWhite,
        )
        Text(
            text = "$percent%",
            style = MaterialTheme.typography.labelMedium,
            color = CustBlack
        )
    }
}