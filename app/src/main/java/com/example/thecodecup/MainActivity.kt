package com.example.thecodecup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
                // Smoke Animation Layers
                SmokeAnimation()
                
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

@Composable
fun SmokeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "smokeTransition")
    
    // Animate alpha of 3 different smoke layers
    val alpha1 by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "alpha1"
    )

    val alpha2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearOutSlowInEasing, delayMillis = 300),
            repeatMode = RepeatMode.Reverse
        ), label = "alpha2"
    )

    val alpha3 by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing, delayMillis = 600),
            repeatMode = RepeatMode.Reverse
        ), label = "alpha3"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.TopCenter
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun OpeningScreenPreview() {
    TheCodeCupTheme {
        OpeningScreen(onFinished = {})
    }
}
