package com.example.thecodecup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.thecodecup.ui.theme.TheCodeCupTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install the system splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCodeCupTheme {
                // State to manage showing the Opening Screen (with smoke animation)
                var showOpeningScreen by remember { mutableStateOf(true) }

                if (showOpeningScreen) {
                    OpeningScreen(onFinished = { showOpeningScreen = false })
                } else {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // Box dùng để căn chỉnh chữ vào chính giữa màn hình sau khi đã trừ đi phần viền hệ thống (innerPadding)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Welcome to The Code Cup!")
        }
    }
}

@Composable
fun OpeningScreen(onFinished: () -> Unit) {
    // Show for 4 seconds to let animation play
    // 1. Bộ đếm thời gian: Khi màn hình này xuất hiện (Unit), ép nó đợi đúng 4 giây (4000ms)
    // để người dùng kịp ngắm hiệu ứng khói, sau đó gọi hàm onFinished() để chuyển sang MainContent.

    LaunchedEffect(Unit) {
        delay(4000)
        onFinished()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Background Image
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Dark Overlay for better contrast
        Surface(
            color = Color.Black.copy(alpha = 0.4f),
            modifier = Modifier.fillMaxSize()
        ) {}

        // 3. Content (Smoke + Cup + Text)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.height(240.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Cup Icon
                Image(
                    painter = painterResource(id = R.drawable.splash_cup),
                    contentDescription = "Cup Icon",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 10.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            // Brand Name
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OpeningScreenPreview() {
    TheCodeCupTheme {
        // Giúp bạn có thể nhìn thấy giao diện màn hình chào ngay trong tab "Design" của Android Studio
        // mà không cần mất công bấm nút chạy máy ảo.
        OpeningScreen(onFinished = {})
    }
}
