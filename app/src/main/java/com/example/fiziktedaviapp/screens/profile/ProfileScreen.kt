package com.example.fiziktedaviapp.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    // Kullanıcı bilgileri (gerçek uygulamada bir model sınıfından gelecek)
    val userName = "Ahmet Yılmaz"
    val userEmail = "ahmet.yilmaz@email.com"
    val totalPoints = 1250
    val totalExercises = 45
    val streak = 7 // Arka arkaya kaç gün egzersiz yapıldığı
    
    // Kazanılan rozetler
    val badges = listOf(
        Badge("Başlangıç", "İlk egzersizini tamamladın", Icons.Outlined.SportsScore, true),
        Badge("Düzenli", "7 gün arka arkaya egzersiz yaptın", Icons.Outlined.Timer, true),
        Badge("Azimli", "30 gün arka arkaya egzersiz yaptın", Icons.Outlined.Star, false),
        Badge("Uzman", "100 egzersiz tamamladın", Icons.Outlined.EmojiEvents, false)
    )
    
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Ayarlar",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        // BottomBar MainActivity'de yönetiliyor, burada gerekli değil
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundLight)
                .verticalScroll(scrollState)
        ) {
            // Profil üst bilgi kartı
            Card(
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkBlue
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profil resmi (şimdilik renk kartı olarak)
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userName.first().toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Kullanıcı adı
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // E-posta
                    Text(
                        text = userEmail,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // İstatistikler
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Toplam puan
                        StatisticItem(
                            value = totalPoints.toString(),
                            label = "Puan",
                            icon = Icons.Outlined.EmojiEvents
                        )
                        
                        // Tamamlanan egzersiz
                        StatisticItem(
                            value = totalExercises.toString(),
                            label = "Egzersiz",
                            icon = Icons.Outlined.FitnessCenter
                        )
                        
                        // Seri
                        StatisticItem(
                            value = "$streak Gün",
                            label = "Seri",
                            icon = Icons.Outlined.Bolt
                        )
                    }
                }
            }
            
            // Sıralama butonu
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceLight
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Leaderboard,
                        contentDescription = null,
                        tint = DarkBlue,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 4.dp)
                    )
                    
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Sıralamayı Gör",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        
                        Text(
                            text = "Diğer kullanıcılarla karşılaştır",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                    
                    Button(
                        onClick = { navController.navigate(Screen.Leaderboard.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MediumBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Görüntüle")
                    }
                }
            }
            
            // Rozetler bölümü
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Rozetler",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                badges.forEach { badge ->
                    BadgeItem(badge = badge)
                }
            }
            
            // Profili düzenle butonu
            Button(
                onClick = { /* TODO: Profil düzenleme ekranına git */ },
                colors = ButtonDefaults.buttonColors(containerColor = MediumBlue),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(text = "Profili Düzenle", fontSize = 16.sp)
            }
            
            // Çıkış butonu
            TextButton(
                onClick = { 
                    // TODO: Çıkış yap ve giriş ekranına git
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = ErrorRed
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Çıkış Yap"
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(text = "Çıkış Yap", fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatisticItem(
    value: String,
    label: String,
    icon: ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun BadgeItem(badge: Badge) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (badge.isEarned) LightBlue.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rozet ikonu
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (badge.isEarned) DarkBlue else Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = badge.icon,
                    contentDescription = badge.name,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = badge.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (badge.isEarned) TextPrimary else Color.Gray
                )
                
                Text(
                    text = badge.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (badge.isEarned) TextSecondary else Color.Gray
                )
            }
            
            if (badge.isEarned) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Kazanıldı",
                    tint = MediumGreen,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Kazanılmadı",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// Rozet veri sınıfı
data class Badge(
    val name: String,
    val description: String,
    val icon: ImageVector,
    val isEarned: Boolean
) 