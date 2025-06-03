package com.example.fiziktedaviapp.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(navController: NavController) {
    // Filtre seçenekleri
    val filterOptions = listOf("Haftalık", "Aylık", "Tüm Zamanlar")
    var selectedFilter by remember { mutableStateOf(filterOptions[0]) }
    
    // Kullanıcı sıralaması (gerçek uygulamada veritabanından gelecek)
    val users = remember {
        listOf(
            LeaderboardUser("Ahmet Y.", 1250, "ahmet.jpg"),
            LeaderboardUser("Mehmet K.", 1450, "mehmet.jpg"),
            LeaderboardUser("Ayşe T.", 1550, "ayse.jpg"),
            LeaderboardUser("Fatma S.", 1120, "fatma.jpg"),
            LeaderboardUser("Ali R.", 980, "ali.jpg"),
            LeaderboardUser("Zeynep O.", 920, "zeynep.jpg"),
            LeaderboardUser("Mustafa N.", 850, "mustafa.jpg"),
            LeaderboardUser("Emine M.", 780, "emine.jpg"),
            LeaderboardUser("Hasan L.", 720, "hasan.jpg"),
            LeaderboardUser("Gülşen K.", 650, "gulsen.jpg")
        ).sortedByDescending { it.points }
    }
    
    // Kullanıcının kendi sıralaması
    val currentUserName = "Ahmet Y."
    val currentUserRank = users.indexOfFirst { it.name == currentUserName } + 1

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Puan Sıralaması") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundLight)
        ) {
            // Filtre butonları
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                filterOptions.forEach { filter ->
                    FilterButton(
                        text = filter,
                        selected = filter == selectedFilter,
                        onClick = { selectedFilter = filter },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            // İlk 3 kullanıcı - özel tasarım
            if (users.size >= 3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    // 2. Sıra
                    TopUserItem(
                        user = users[1],
                        rank = 2,
                        modifier = Modifier.weight(1f),
                        containerHeight = 100.dp,
                        avatarSize = 56.dp,
                        crownIcon = Icons.Filled.Filter2
                    )
                    
                    // 1. Sıra
                    TopUserItem(
                        user = users[0],
                        rank = 1,
                        modifier = Modifier.weight(1f),
                        containerHeight = 130.dp,
                        avatarSize = 72.dp,
                        crownIcon = Icons.Filled.Filter1
                    )
                    
                    // 3. Sıra
                    TopUserItem(
                        user = users[2],
                        rank = 3,
                        modifier = Modifier.weight(1f),
                        containerHeight = 80.dp,
                        avatarSize = 48.dp,
                        crownIcon = Icons.Filled.Filter3
                    )
                }
            }
            
            // Diğer kullanıcılar
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceLight
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Kalan tüm alanı kapla
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // En tepede başlık
                    Text(
                        text = "Sıralama",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    // Sütun başlıkları
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sıra",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            modifier = Modifier.width(48.dp)
                        )
                        
                        Text(
                            text = "Kullanıcı",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            modifier = Modifier.weight(1f)
                        )
                        
                        Text(
                            text = "Puan",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            textAlign = TextAlign.End,
                            modifier = Modifier.width(60.dp)
                        )
                    }
                    
                    Divider(color = LightBlue.copy(alpha = 0.5f))
                    
                    // Kullanıcı listesi (ilk 3 hariç)
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // İlk 3'ten sonraki kullanıcılar
                        itemsIndexed(users.drop(3)) { index, user ->
                            val rank = index + 4 // İlk 3'ü atladık
                            LeaderboardRow(
                                user = user,
                                rank = rank,
                                isCurrentUser = user.name == currentUserName
                            )
                        }
                    }
                    
                    Divider(color = LightBlue.copy(alpha = 0.5f))
                    
                    // En altta kullanıcının kendi pozisyonu
                    if (currentUserRank > 3) {
                        val currentUser = users.first { it.name == currentUserName }
                        
                        Text(
                            text = "Senin Sıran",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )
                        
                        LeaderboardRow(
                            user = currentUser,
                            rank = currentUserRank,
                            isCurrentUser = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (selected) MediumBlue else TextSecondary
        ),
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
            
            if (selected) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(width = 24.dp, height = 2.dp)
                        .background(MediumBlue)
                )
            }
        }
    }
}

@Composable
fun TopUserItem(
    user: LeaderboardUser,
    rank: Int,
    modifier: Modifier = Modifier,
    containerHeight: Dp,
    avatarSize: Dp,
    crownIcon: ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Rozet - sıralama göstergesi
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(
                    when (rank) {
                        1 -> Color(0xFFFFD700) // Altın
                        2 -> Color(0xFFC0C0C0) // Gümüş
                        3 -> Color(0xFFCD7F32) // Bronz
                        else -> Color.Gray
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = crownIcon,
                contentDescription = "Sıra $rank",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
        
        // Profil resmi (renk kartı)
        Box(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .size(avatarSize)
                .clip(CircleShape)
                .background(
                    when (rank) {
                        1 -> MediumBlue
                        2 -> DarkBlue.copy(alpha = 0.8f)
                        3 -> DarkBlue.copy(alpha = 0.6f)
                        else -> DarkBlue.copy(alpha = 0.4f)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user.name.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = when (rank) {
                    1 -> 24.sp
                    2 -> 20.sp
                    else -> 16.sp
                }
            )
        }
        
        // Kullanıcı adı
        Text(
            text = user.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        
        // Puan
        Text(
            text = "${user.points} puan",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LeaderboardRow(
    user: LeaderboardUser,
    rank: Int,
    isCurrentUser: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isCurrentUser) LightBlue.copy(alpha = 0.15f) else Color.Transparent
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Sıra
        Text(
            text = "$rank.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = if (isCurrentUser) DarkBlue else TextPrimary,
            modifier = Modifier.width(48.dp)
        )
        
        // Kullanıcı
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            // Profil resmi
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(if (isCurrentUser) MediumBlue else LightBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.name.first().toString(),
                    color = if (isCurrentUser) Color.White else DarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            
            // Kullanıcı adı
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isCurrentUser) DarkBlue else TextPrimary,
                fontWeight = if (isCurrentUser) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        
        // Puan
        Text(
            text = "${user.points}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = if (isCurrentUser) DarkBlue else TextPrimary,
            textAlign = TextAlign.End,
            modifier = Modifier.width(60.dp)
        )
    }
}

// Leaderboard kullanıcı veri sınıfı
data class LeaderboardUser(
    val name: String,
    val points: Int,
    val avatarUrl: String
) 