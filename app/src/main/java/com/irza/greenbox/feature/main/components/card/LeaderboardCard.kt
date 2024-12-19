package com.irza.greenbox.feature.main.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.irza.greenbox.R
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustLightGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun LeaderboardCard(uid:String, id: String ,rank: Int, name: String, points: Int, type: Int) {

    fun processName(name: String): String {
        return if (name.length > 12) {
            name.split(" ").firstOrNull() ?: ""
        } else {
            name
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = if(type == 0) if (rank%2 == 0)  CustGreen else CustLightGreen else if(uid == id) CustWhite else CustLightGreen)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .align(Alignment.TopStart),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = rank.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = processName(name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if(type == 0) if (rank%2 == 0)  CustWhite else CustBlack else CustBlack
                )
            }

            // Points
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "$points pts",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if(type == 0) if (rank%2 == 0)  CustWhite else CustBlack else CustBlack
                )
            }
        }
    }
}