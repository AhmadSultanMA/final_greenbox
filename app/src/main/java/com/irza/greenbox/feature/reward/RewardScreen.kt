package com.irza.greenbox.feature.reward

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.feature.main.components.appBar.AppBar
import com.irza.greenbox.feature.main.components.card.PointCard
import com.irza.greenbox.feature.main.components.card.RewardCard
import com.irza.greenbox.feature.main.navigation.BottomNavigationBar
import com.irza.greenbox.ui.theme.CustGreen

@Composable
fun RewardScreen(navController: NavController) {
    val viewModel: RewardViewModel = viewModel()

    Scaffold(
        topBar = { AppBar(navController, viewModel.user.value?.nama ?: "") },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = CustGreen,
                            shape = RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    PointCard((viewModel.user.value?.point ?: 0).toString(), "Total", "Valid until 31 Dec 2024")
                }
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Let’s Redeem your points",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.reward.size) { index ->
                RewardCard(
                    viewModel.reward[index].id,
                    viewModel.reward[index].nama,
                    viewModel.reward[index].deskripsi,
                    viewModel.reward[index].point.toString(),
                    navController
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}