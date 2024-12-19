package com.irza.greenbox.feature.redeem

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.buttons.PrimaryButton
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustWhite
import com.irza.greenbox.ui.theme.CustWhiteGreen

@Composable
fun RedeemScreen(navController: NavController, id: String) {
    val viewModel: RedeemViewModel = viewModel()

    LaunchedEffect(key1 = true){
        viewModel.getRewardById(id)
    }

    val descriptionList = viewModel.reward.value?.deskripsi?.split(", ")?.map { it.trim() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustWhite)
    ) {
        // Image Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.reward),
                contentDescription = "Cleaning Kit",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(color = CustWhiteGreen),
                contentScale = ContentScale.Crop,
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.Reward.route) {
                            popUpTo(Screen.Redeem.route) {
                                inclusive = true
                            }
                        }
                    }
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = viewModel.reward.value?.nama ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
                Text(
                    text = "Can be redeemed until 31 Dec 2024",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = (viewModel.reward.value?.point ?: 0).toString() + "pts",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Items List Section
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                descriptionList?.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FiberManualRecord,
                            contentDescription = null,
                            tint = Color.Green,
                            modifier = Modifier.size(8.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Points and Redeem Button Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {
            Text(
                text = "Your points",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = viewModel.user.value?.point.toString() + "pts",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(64.dp))


            PrimaryButton(text = "Redeem") {
//
            }

        }
    }
}

