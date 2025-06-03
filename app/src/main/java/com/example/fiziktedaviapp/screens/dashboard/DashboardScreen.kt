package com.example.fiziktedaviapp.screens.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.components.*
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    
    // Screen width for responsive layout adjustments
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    // Kullanıcı bilgileri (daha sonra viewModel'dan alınacak)
    val userName = "Ahmet Yılmaz"
    val userRole = "Hasta"
    
    // Bugünün tarihi
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val formattedDate = remember { currentDate.format(dateFormatter) }
    
    // Bugünkü ve toplam egzersiz istatistikleri
    val completionPercentage = 75f
    val completedExercises = 4
    val totalExercises = 5
    val streakDays = 12
    val totalPoints = 450
    val weeklyGoal = 5
    val todaysExercises = 3
    
    // Animasyonlar
    val progressAnimation by animateFloatAsState(
        targetValue = completionPercentage,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "progress_animation"
    )
    
    // Motivasyon cümleleri
    val motivationalQuotes = listOf(
        "Bugün de harika gidiyorsun! 💪",
        "Her adım seni hedefe yaklaştırıyor ✨",
        "Mükemmel bir ilerleme gösteriyorsun! 🌟",
        "Kararlılığın ilham verici! 🎯",
        "Sağlıklı yaşam yolculuğunda emin adımlarla ilerliyorsun! 🚀"
    )
    
    val randomQuote = remember { motivationalQuotes[Random.nextInt(motivationalQuotes.size)] }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryBlue50,
                        SurfaceCream,
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Modern Header Section
            ModernHeaderSection(
                userName = userName,
                formattedDate = formattedDate,
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Welcome Message with Glass Effect
            WelcomeGlassCard(
                quote = randomQuote,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Progress Summary Cards
            ProgressSummarySection(
                completionPercentage = progressAnimation,
                completedExercises = completedExercises,
                totalExercises = totalExercises,
                streakDays = streakDays,
                totalPoints = totalPoints,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Quick Actions Section
            QuickActionsSection(
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Today's Exercise Card
            TodaysExerciseCard(
                todaysExercises = todaysExercises,
                weeklyGoal = weeklyGoal,
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(100.dp)) // Space for bottom navigation
        }
        
        // Floating Emergency Button
        FloatingEmergencyButton(
            onClick = { navController.navigate(Screen.TherapistCall.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

@Composable
fun ModernHeaderSection(
    userName: String,
    formattedDate: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    GradientGlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        gradientColors = listOf(
            PrimaryBlue400.copy(alpha = 0.15f),
            WellnessTeal400.copy(alpha = 0.1f),
            HealthGreen400.copy(alpha = 0.08f)
        ),
        cornerRadius = 24.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary700,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hoş geldin,",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary700
                )
                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary900
                )
            }
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AnimatedIconButton(
                    icon = Icons.Default.Notifications,
                    onClick = onNotificationClick,
                    backgroundColor = PrimaryBlue100,
                    contentColor = PrimaryBlue700,
                    size = 44.dp
                )
                
                AnimatedIconButton(
                    icon = Icons.Default.Settings,
                    onClick = onSettingsClick,
                    backgroundColor = HealthGreen100,
                    contentColor = HealthGreen700,
                    size = 44.dp
                )
                
                // Profile Picture
                Surface(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(onClick = onProfileClick),
                    shape = CircleShape,
                    color = PrimaryBlue500,
                    shadowElevation = 4.dp
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userName.first().toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeGlassCard(
    quote: String,
    modifier: Modifier = Modifier
) {
    GradientGlassCard(
        modifier = modifier,
        gradientColors = listOf(
            WellnessTeal400.copy(alpha = 0.12f),
            HealthGreen400.copy(alpha = 0.08f),
            Color.Transparent
        ),
        cornerRadius = 20.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = WellnessTeal100
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = WellnessTeal700,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(16.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Günün Motivasyonu",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = WellnessTeal700
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quote,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary900,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProgressSummarySection(
    completionPercentage: Float,
    completedExercises: Int,
    totalExercises: Int,
    streakDays: Int,
    totalPoints: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Main Progress Card
        AnimatedProgressCard(
            title = "Bugünkü İlerleme",
            progress = completionPercentage,
            unit = "%",
            icon = Icons.Default.TrendingUp,
            progressColor = HealthGreen500,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Tamamlanan",
                value = "$completedExercises/$totalExercises",
                subtitle = "egzersiz",
                icon = Icons.Default.CheckCircle,
                color = HealthGreen500,
                modifier = Modifier.weight(1f)
            )
            
            StatCard(
                title = "Seri",
                value = "$streakDays",
                subtitle = "gün",
                icon = Icons.Default.LocalFireDepartment,
                color = WarningOrange,
                modifier = Modifier.weight(1f)
            )
            
            StatCard(
                title = "Puan",
                value = "$totalPoints",
                subtitle = "toplam",
                icon = Icons.Default.Stars,
                color = PrimaryBlue500,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    GradientGlassCard(
        modifier = modifier,
        gradientColors = listOf(
            color.copy(alpha = 0.1f),
            color.copy(alpha = 0.05f),
            Color.Transparent
        ),
        cornerRadius = 16.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary900
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary700
            )
            
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = color,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun QuickActionsSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Hızlı Eylemler",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextPrimary900,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            item {
                ModernActionCard(
                    title = "Egzersize Başla",
                    subtitle = "Günlük programını tamamla",
                    icon = Icons.Default.PlayArrow,
                    colors = listOf(HealthGreen400, HealthGreen600),
                    onClick = { navController.navigate(Screen.ExerciseList.route) }
                )
            }
            
            item {
                ModernActionCard(
                    title = "İlerleme",
                    subtitle = "Geçmiş performansını gör",
                    icon = Icons.Default.Timeline,
                    colors = listOf(PrimaryBlue400, PrimaryBlue600),
                    onClick = { navController.navigate(Screen.ExerciseCalendar.route) }
                )
            }
            
            item {
                ModernActionCard(
                    title = "Sıralama",
                    subtitle = "Liderlik tablosunu incele",
                    icon = Icons.Default.EmojiEvents,
                    colors = listOf(WarningOrange, Color(0xFFF57C00)),
                    onClick = { navController.navigate(Screen.Leaderboard.route) }
                )
            }
        }
    }
}

@Composable
fun ModernActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    colors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .width(200.dp)
            .height(120.dp),
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 8.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = colors,
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(
                            Float.POSITIVE_INFINITY,
                            Float.POSITIVE_INFINITY
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
fun TodaysExerciseCard(
    todaysExercises: Int,
    weeklyGoal: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    GradientGlassCard(
        modifier = modifier,
        onClick = { navController.navigate(Screen.ExerciseList.route) },
        gradientColors = listOf(
            PrimaryBlue400.copy(alpha = 0.12f),
            WellnessTeal400.copy(alpha = 0.08f),
            Color.Transparent
        ),
        cornerRadius = 20.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Bugünkü Egzersizler",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary900
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "$todaysExercises egzersiz hazır",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary700
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                GradientButton(
                    text = "Başla",
                    onClick = { navController.navigate(Screen.ExerciseList.route) },
                    gradientColors = listOf(HealthGreen400, HealthGreen600),
                    leadingIcon = Icons.Default.PlayArrow,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = HealthGreen100
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = HealthGreen600,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(20.dp)
                )
            }
        }
    }
}

@Composable
fun FloatingEmergencyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PulseFloatingActionButton(
        icon = Icons.Default.Phone,
        onClick = onClick,
        backgroundColor = ErrorRed,
        contentColor = Color.White,
        size = 64.dp,
        isPulsing = true,
        modifier = modifier
    )
} 