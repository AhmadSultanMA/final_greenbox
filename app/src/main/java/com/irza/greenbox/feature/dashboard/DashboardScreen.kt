package com.irza.greenbox.feature.dashboard

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.appBar.AppBar
import com.irza.greenbox.feature.main.components.card.CardDaily
import com.irza.greenbox.feature.main.components.card.LeaderboardCard
import com.irza.greenbox.feature.main.components.card.PostCard
import com.irza.greenbox.feature.main.components.card.VideoCard
import com.irza.greenbox.feature.main.components.indicator.LinearIndicator
import com.irza.greenbox.feature.main.navigation.BottomNavigationBar
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.model.cardData.CardData
import com.irza.greenbox.model.videoData.VideoData
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(navController: NavController) {
    val dataList = listOf(
        CardData("Plastic Bag", "kg", 1, 50),
        CardData("Styrofoam", "g", 500, 30),
        CardData("Bottle", "pcs", 30, 100),
        CardData("Pipe PVC", "g", 800, 30)
    )

    val videoDataList = listOf(
        VideoData(judul = "Merangkul Kegelisahan: Sampah di Pantai dan Pemulihannya", imageRes = R.drawable.video),
        VideoData(judul = "Perjuangan Bersama: Mengatasi Pembuangan Sampah di Komplek Perumahan", imageRes = R.drawable.video)
    )

    val viewModel: DashboardViewModel = viewModel()

    LaunchedEffect(key1 = true){
        viewModel.getAllUserByPoint()
    }


    val leaderboard = viewModel.leaderboard.take(3)

    Scaffold(
        topBar = { AppBar(navController, viewModel.user.value?.nama ?: "") },
        bottomBar = { BottomNavigationBar(navController = navController)}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20))
                        .background(color = CustGreen)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = viewModel.user.value?.point.toString(),
                                style = MaterialTheme.typography.displayLarge,
                                color = CustWhite
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "pts",
                                style = MaterialTheme.typography.headlineMedium,
                                color = CustWhite
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearIndicator(percent = 75)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Daily Goals", style = MaterialTheme.typography.titleLarge, color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

            }

            items(dataList.chunked(2)) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),

                    ) {
                    row.forEach { cardData ->
                        CardDaily(
                            name = cardData.name,
                            satuan = cardData.satuan,
                            banyak = cardData.banyak,
                            percent = cardData.percent,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Leaderboard", style = MaterialTheme.typography.titleLarge, color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(leaderboard.size){
                index: Int ->
                LeaderboardCard(
                    uid = viewModel.auth.uid ?: "",
                    id = viewModel.leaderboard[index].uid,
                    rank = index + 1,
                    name = viewModel.leaderboard[index].nama,
                    points = viewModel.leaderboard[index].point.toInt(),
                    type = 0
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Videos", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                    Text(text = "More", style = MaterialTheme.typography.bodyLarge, color = CustGreen)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(videoDataList.chunked(2)) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),

                    ) {
                    row.forEach { videoData ->
                        VideoCard(
                            judul = videoData.judul,
                            imageRes = videoData.imageRes,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Featured Posts", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                    Text(text = "More", style = MaterialTheme.typography.bodyLarge, color = CustGreen)
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(viewModel.postData.chunked(2)) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),

                    ) {
                    row.forEach { postData ->
                        PostCard(
                            navController,
                            id = postData.id,
                            judul = postData.judul,
                            category = postData.kategori,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }

}