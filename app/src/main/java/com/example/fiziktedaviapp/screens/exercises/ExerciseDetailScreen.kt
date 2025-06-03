package com.example.fiziktedaviapp.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.model.Exercise
import com.example.fiziktedaviapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseId: String) {
    // Gerçek uygulamada veritabanından veya API'den alınacak
    // Şimdilik örnek veri kullanıyoruz
    val exercise = remember {
        when (exerciseId) {
            "1" -> Exercise(
                id = "1",
                name = "Diz Bükme Germe",
                bodyPart = "Diz",
                duration = "5 dakika",
                description = "Bu egzersiz diz ekleminizin hareket aralığını artırmaya ve kaslarınızı güçlendirmeye yardımcı olur. Düzenli yapıldığında diz ağrılarını hafifletebilir.",
                repetitions = "3 set, her sette 15 tekrar",
                videoUrl = null,
                instructions = listOf(
                    "Düz bir zeminde sırt üstü uzanın",
                    "Dizinizi yavaşça göğsünüze doğru çekin",
                    "3 saniye tutun ve yavaşça başlangıç pozisyonuna dönün",
                    "Her iki diziniz için tekrarlayın"
                ),
                safetyTips = "Dizinizde ani ağrı hissederseniz, egzersizi durdurun ve doktorunuza danışın."
            )
            "2" -> Exercise(
                id = "2",
                name = "Bel Rotasyon Egzersizi",
                bodyPart = "Bel",
                duration = "8 dakika",
                description = "Bu egzersiz, bel bölgesindeki gerginliği azaltmaya ve omurga esnekliğini artırmaya yardımcı olur.",
                repetitions = "Her iki tarafa 10 tekrar",
                videoUrl = null,
                instructions = listOf(
                    "Sırtüstü yatın, dizlerinizi bükün ve ayaklarınızı yere basın",
                    "Kollarınızı yana doğru açın",
                    "Her iki dizinizi birlikte sağa doğru indirin",
                    "3-5 saniye tutun, sonra merkeze dönün",
                    "Aynı hareketi sol tarafa tekrarlayın"
                ),
                safetyTips = "Hareket sırasında omuzlarınızın yerden kalkmamasına dikkat edin."
            )
            else -> Exercise(
                id = "3",
                name = "Örnek Egzersiz",
                bodyPart = "Genel",
                duration = "5 dakika",
                description = "Bu bir örnek egzersiz açıklamasıdır.",
                repetitions = "3 set, her sette 10 tekrar",
                videoUrl = null,
                instructions = listOf(
                    "Adım 1: Başlangıç pozisyonunu alın",
                    "Adım 2: Hareketi gerçekleştirin",
                    "Adım 3: Başlangıç pozisyonuna dönün"
                ),
                safetyTips = "Egzersiz sırasında nefes almayı unutmayın."
            )
        }
    }
    
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exercise.name) },
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
                },
                actions = {
                    IconButton(onClick = { /* TODO: Favorilere ekle */ }) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Favorilere Ekle",
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
                .verticalScroll(scrollState)
        ) {
            // Video gösterimi (şimdilik renkli bir kutu)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(DarkBlue.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayCircle,
                    contentDescription = "Video",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
            
            // Egzersiz detay bilgileri
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Egzersiz bilgisi başlığı
                Text(
                    text = "Egzersiz Bilgisi",
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Detay kartı
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SurfaceLight
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Bölge bilgisi
                        DetailItem(
                            icon = Icons.Outlined.Label,
                            title = "Bölge",
                            value = exercise.bodyPart
                        )
                        
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = LightBlue
                        )
                        
                        // Süre bilgisi
                        DetailItem(
                            icon = Icons.Outlined.Timer,
                            title = "Süre",
                            value = exercise.duration
                        )
                        
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = LightBlue
                        )
                        
                        // Tekrar bilgisi
                        DetailItem(
                            icon = Icons.Outlined.Repeat,
                            title = "Tekrar",
                            value = exercise.repetitions
                        )
                    }
                }
                
                // Açıklama
                Text(
                    text = "Açıklama",
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = exercise.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Adımlar
                Text(
                    text = "Nasıl Yapılır",
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SurfaceLight
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        exercise.instructions.forEachIndexed { index, instruction ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "${index + 1}.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = DarkBlue,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                                )
                                
                                Text(
                                    text = instruction,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = TextPrimary
                                )
                            }
                            
                            if (index < exercise.instructions.size - 1) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
                
                // Güvenlik İpuçları
                if (exercise.safetyTips.isNotEmpty()) {
                    Text(
                        text = "Dikkat Edilmesi Gerekenler",
                        style = MaterialTheme.typography.titleLarge,
                        color = DarkBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = WarningYellow.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Warning,
                                contentDescription = null,
                                tint = WarningYellow,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .size(24.dp)
                            )
                            
                            Text(
                                text = exercise.safetyTips,
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextPrimary
                            )
                        }
                    }
                }
                
                // Egzersize Başla butonu
                Button(
                    onClick = { /* TODO: Egzersiz başlatma işlemleri */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MediumGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(text = "Egzersize Başla", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MediumBlue,
            modifier = Modifier.size(24.dp)
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = TextPrimary
            )
        }
    }
} 