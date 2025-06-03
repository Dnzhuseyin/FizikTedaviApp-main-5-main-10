package com.example.fiziktedaviapp.screens.therapist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.ui.theme.*

@Composable
fun TherapistCallScreen(navController: NavController) {
    var isCallConnected by remember { mutableStateOf(true) }
    var isAudioMuted by remember { mutableStateOf(false) }
    var isVideoEnabled by remember { mutableStateOf(true) }
    var isSpeakerOn by remember { mutableStateOf(true) }
    
    // Terapist bilgileri
    val therapistName = "Dr. Ayşe Demir"
    val callDuration = "05:32" // Gerçek uygulamada bu bir sayaç olacak
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Ana görüntü - Terapist
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Görüntülü arama yerine renk arka planı kullanıyoruz
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue.copy(alpha = 0.8f))
            )
            
            // Görüntü yoksa veya devre dışı bırakıldıysa
            if (!isVideoEnabled) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Profil resmi
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = therapistName.first().toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = therapistName,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "Video Kapalı",
                        color = Color.White.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            // Üst bilgi çubuğu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Geri butonu
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Geri",
                        tint = Color.White
                    )
                }
                
                // Terapist adı ve süre
                if (isCallConnected) {
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = callDuration,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                
                // Kamera değiştirme butonu
                IconButton(
                    onClick = { /* Kamera değiştir */ },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.Default.FlipCameraAndroid,
                        contentDescription = "Kamera Değiştir",
                        tint = Color.White
                    )
                }
            }
            
            // Kullanıcının küçük kamera görüntüsü
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(width = 120.dp, height = 160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MediumBlue)
            ) {
                // Kamera kapalıysa
                if (!isVideoEnabled) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.VideocamOff,
                            contentDescription = "Kamera Kapalı",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            
            // Alttaki kontrol butonları
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                // Not alma butonu
                FloatingActionButton(
                    onClick = { /* Not al */ },
                    containerColor = Color.White,
                    contentColor = DarkBlue,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 24.dp, bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NoteAdd,
                        contentDescription = "Not Al"
                    )
                }
                
                // Ana kontrol butonları
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Mikrofon
                    CallControlButton(
                        icon = if (isAudioMuted) Icons.Default.MicOff else Icons.Default.Mic,
                        description = if (isAudioMuted) "Mikrofonu Aç" else "Mikrofonu Kapat",
                        backgroundColor = if (isAudioMuted) ErrorRed else Color.White.copy(alpha = 0.2f),
                        onClick = { isAudioMuted = !isAudioMuted }
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    // Çağrıyı Sonlandır
                    CallControlButton(
                        icon = Icons.Default.CallEnd,
                        description = "Çağrıyı Sonlandır",
                        backgroundColor = ErrorRed,
                        size = 64.dp,
                        iconSize = 32.dp,
                        onClick = { 
                            // Çağrıyı sonlandır ve ana ekrana dön
                            navController.navigateUp()
                        }
                    )
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    // Video
                    CallControlButton(
                        icon = if (isVideoEnabled) Icons.Default.Videocam else Icons.Default.VideocamOff,
                        description = if (isVideoEnabled) "Videoyu Kapat" else "Videoyu Aç",
                        backgroundColor = if (isVideoEnabled) Color.White.copy(alpha = 0.2f) else ErrorRed,
                        onClick = { isVideoEnabled = !isVideoEnabled }
                    )
                }
                
                // Hoparlör butonu
                FloatingActionButton(
                    onClick = { isSpeakerOn = !isSpeakerOn },
                    containerColor = if (isSpeakerOn) Color.White else Color.White.copy(alpha = 0.2f),
                    contentColor = if (isSpeakerOn) DarkBlue else Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp, bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = if (isSpeakerOn) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                        contentDescription = if (isSpeakerOn) "Hoparlörü Kapat" else "Hoparlörü Aç"
                    )
                }
            }
            
            // Bağlantı durumu bildirimi
            if (!isCallConnected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Bağlanıyor...",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Text(
                            text = therapistName,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CallControlButton(
    icon: ImageVector,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    size: Dp = 56.dp,
    iconSize: Dp = 24.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.White,
            modifier = Modifier.size(iconSize)
        )
    }
} 