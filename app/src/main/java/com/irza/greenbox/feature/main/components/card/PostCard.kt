package com.irza.greenbox.feature.main.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.irza.greenbox.ui.theme.CustBeige
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustLightGreen
import com.irza.greenbox.ui.theme.CustOrange

@Composable
fun PostCard(judul: String, @DrawableRes imageRes: Int, category: String, modifier: Modifier = Modifier) {
    val trimmedJudul = judul.split(" ").take(5).joinToString(" ") +
            if (judul.split(" ").size > 5) "..." else ""
    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CustLightGreen)
                    .padding(16.dp)
            ) {
                // Tag "News"
                Text(
                    text = category,
                    color = CustBlack,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(
                            color = if (category == "News")  CustBeige else CustOrange,
                            shape = RoundedCornerShape(100)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = trimmedJudul,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = CustBlack
                )
            }
        }
    }
}