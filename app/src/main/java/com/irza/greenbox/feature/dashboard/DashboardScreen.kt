package com.irza.greenbox.feature.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.greenbox.feature.main.components.card.CardDaily
import com.irza.greenbox.feature.main.components.indicator.LinearIndicator
import com.irza.greenbox.model.cardData.CardData
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController) {
    val dataList = listOf(
        CardData("Plastic Bag", "kg", 1, 50),
        CardData("Styrofoam", "g", 500, 30),
        CardData("Bottle", "pcs", 30, 100),
        CardData("Pipe PVC", "g", 800, 30)
    )

    Scaffold() {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ){
            item {
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
                            Text(text = "1230", style = MaterialTheme.typography.displayLarge, color = CustWhite)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "pts", style = MaterialTheme.typography.headlineMedium, color = CustWhite)
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))

                        LinearIndicator(percent = 75)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Daily Goals", style = MaterialTheme.typography.titleLarge)

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
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Leaderboard", style = MaterialTheme.typography.titleLarge)
            }

        }
    }

}