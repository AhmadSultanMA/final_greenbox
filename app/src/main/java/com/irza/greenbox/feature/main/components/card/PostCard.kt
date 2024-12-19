package com.irza.greenbox.feature.main.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustBeige
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustLightGreen
import com.irza.greenbox.ui.theme.CustOrange

@Composable
fun PostCard(navController: NavController, id: String ,judul: String, category: String, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10))
            .clickable {
                navController.navigate("${Screen.PostDetail.route}/${id}") {
                    popUpTo(Screen.Dashboard.route) {
                        inclusive = true
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.post),
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
                    text = judul,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    color = CustBlack
                )
            }
        }
    }
}