package com.irza.greenbox.feature.postDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustBeige
import com.irza.greenbox.ui.theme.CustOrange

@Composable
fun PostDetailScreen(navController: NavController, id: String) {
    val viewModel: PostDetailViewModel = viewModel()

    LaunchedEffect(key1 = true){
        viewModel.getPostById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        // Image Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.post),
                contentDescription = "News Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.PostDetail.route) {
                                inclusive = true
                            }
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = viewModel.post.value?.kategori ?: "",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .background(color = if (viewModel.post.value?.kategori == "News") CustBeige else CustOrange, shape = RoundedCornerShape(100))
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = viewModel.post.value?.judul ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Jakarta, Indonesia",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewModel.post.value?.isi ?: "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}