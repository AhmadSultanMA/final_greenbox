package com.irza.greenbox.feature.main.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.irza.greenbox.feature.main.components.indicator.LinearIndicator
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun CardDaily(name: String, satuan: String, banyak: Int, percent: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(150.dp)
            .clip(shape = RoundedCornerShape(10))
            .background(color = CustGreen)
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        Column(
            Modifier.fillMaxSize().background(color = CustGreen),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, style = MaterialTheme.typography.headlineSmall, color = CustWhite)
            Row {
                Text(text = banyak.toString(), style = MaterialTheme.typography.displaySmall, color = CustWhite)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = satuan, style = MaterialTheme.typography.displaySmall, color = CustWhite)
            }
            LinearIndicator(percent = percent)
        }
    }
}