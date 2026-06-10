package com.example.thecodecup.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.thecodecup.ui.theme.TheCodeCupTheme
import com.example.thecodecup.R
import com.example.thecodecup.data.model.Coffee
import com.example.thecodecup.ui.components.CoffeeCard

@Composable
fun HomeScreen(
    onCoffeeClick: (Coffee) -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val coffeeList = listOf(
        Coffee("1", "Americano", price = 3.0, imageRes = R.drawable.americano),
        Coffee("2", "Cappuccino", price = 3.5, imageRes = R.drawable.capuchino),
        Coffee("3", "Mocha", price = 4.0, imageRes = R.drawable.mocha),
        Coffee("4", "Flat White", price = 3.5, imageRes = R.drawable.flat_white)
    )

    Scaffold(
        topBar = {
            HomeHeader(
                userName = "Anderson",
                onCartClick = onCartClick,
                onProfileClick = onProfileClick
            )
        },
        bottomBar = {
            HomeBottomBar()
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            
            LoyaltyCardSection()
            
            Spacer(modifier = Modifier.height(50.dp))
            
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF324A59),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Choose your coffee",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(coffeeList) { coffee ->
                            CoffeeCard(coffee = coffee, onClick = onCoffeeClick)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun HomeHeader(
    userName: String,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Good morning", color = Color(0xFFD8D8D8), fontSize = 20.sp)
            Text(text = userName, color = Color(0xFF181D23), fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        
        Row {
            IconButton(onClick = onCartClick) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color(0xFF181D23))
            }
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFF181D23))
            }
        }
    }
}

@Composable
fun LoyaltyCardSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF324A59))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Loyalty card", color = Color(0xFFD8D8D8), fontSize = 14.sp)
                Text(text = "4 / 8", color = Color(0xFFD8D8D8), fontSize = 14.sp)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(8) { index ->
                        Image(
                            painter = painterResource(
                                id = if (index < 4) R.drawable.fill_stamp else R.drawable.unfilled_stamp
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TheCodeCupTheme {
        HomeScreen(
            onCoffeeClick = {},
            onCartClick = {},
            onProfileClick = {}
        )
    }
}

@Composable
fun HomeBottomBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp // Clean look
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF324A59),
                indicatorColor = Color(0xFFEFEFEF)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.CardGiftcard, contentDescription = "Rewards") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.History, contentDescription = "Orders") }
        )
    }
}

