package com.irza.greenbox.feature.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.appBar.AppBar
import com.irza.greenbox.feature.main.components.card.PostCard
import com.irza.greenbox.feature.main.components.card.VideoCard
import com.irza.greenbox.feature.main.navigation.BottomNavigationBar
import com.irza.greenbox.model.videoData.VideoData
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun FeedScreen(navController: NavController) {
    val viewModel: FeedViewModel = viewModel()

    val videoDataList = listOf(
        VideoData(judul = "Merangkul Kegelisahan: Sampah di Pantai dan Pemulihannya", imageRes = R.drawable.video),
        VideoData(judul = "Perjuangan Bersama: Mengatasi Pembuangan Sampah di Komplek Perumahan", imageRes = R.drawable.video),
    )

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
                        .height(80.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .background(color = CustGreen, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                    contentAlignment = Alignment.Center,
                ){
                    Text("Your Feed", style = MaterialTheme.typography.displaySmall, color = CustWhite)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Videos", style = MaterialTheme.typography.titleLarge, color = Color.Black, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(videoDataList.chunked(2)) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),

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
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Posts", style = MaterialTheme.typography.titleLarge, color = Color.Black, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(viewModel.postData.chunked(2)) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),

                    ) {
                    row.forEach { postData ->
                        PostCard(
                            navController,
                            id = postData.id,
                            judul = postData.judul,
                            category = postData.kategori ,
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}