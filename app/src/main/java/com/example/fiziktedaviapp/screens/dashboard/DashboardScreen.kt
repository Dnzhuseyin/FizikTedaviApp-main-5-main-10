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
    // Kullanıcı bilgileri (daha sonra viewModel'dan alınacak)
    val userName = "Ahmet Yılmaz"
    
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

    ResponsivePage(
        backgroundColor = MaterialTheme.colorScheme.background,
        scrollable = true,
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
    ) { config ->
        // Floating Emergency Button için container
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                // Modern Header Section
                ModernHeaderSection(
                    userName = userName,
                    formattedDate = formattedDate,
                    onProfileClick = { navController.navigate(Screen.Profile.route) },
                    onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                    onSettingsClick = { navController.navigate(Screen.Settings.route) },
                    config = config
                )
                
                Spacer(modifier = Modifier.height(config.verticalSpacing()))
                
                // Welcome Message with Glass Effect
                WelcomeGlassCard(
                    quote = randomQuote,
                    config = config
                )
                
                Spacer(modifier = Modifier.height(config.verticalSpacing()))
                
                // Progress Summary Cards
                ProgressSummarySection(
                    completionPercentage = progressAnimation,
                    completedExercises = completedExercises,
                    totalExercises = totalExercises,
                    streakDays = streakDays,
                    totalPoints = totalPoints,
                    config = config
                )
                
                Spacer(modifier = Modifier.height(config.verticalSpacing()))
                
                // Quick Actions Section
                QuickActionsSection(
                    navController = navController,
                    config = config
                )
                
                Spacer(modifier = Modifier.height(config.verticalSpacing()))
                
                // Today's Exercise Card
                TodaysExerciseCard(
                    todaysExercises = todaysExercises,
                    weeklyGoal = weeklyGoal,
                    navController = navController,
                    config = config
                )
                
                // Bottom spacing for floating button
                Spacer(modifier = Modifier.height(config.verticalSpacing(32.dp)))
            }
            
            // Floating Emergency Button
            FloatingEmergencyButton(
                onClick = { navController.navigate(Screen.TherapistCall.route) },
                config = config,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(config.componentPadding())
            )
        }
    }
}

@Composable
fun ModernHeaderSection(
    userName: String,
    formattedDate: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    config: ResponsiveConfig
) {
    GradientGlassCard(
        modifier = Modifier.fillMaxWidth(),
        gradientColors = listOf(
            PrimaryBlue400.copy(alpha = 0.15f),
            WellnessTeal400.copy(alpha = 0.1f),
            HealthGreen400.copy(alpha = 0.08f)
        ),
        cornerRadius = config.adaptiveHeight(
            extraSmall = 16.dp,
            compact = 20.dp,
            medium = 24.dp,
            expanded = 28.dp
        )
    ) {
        when (config.screenSizeClass) {
            ScreenSizeClass.EXTRA_SMALL -> {
                // Çok küçük ekranlar için compact layout
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formattedDate,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary700
                        )
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            AnimatedIconButton(
                                icon = Icons.Default.Notifications,
                                onClick = onNotificationClick,
                                backgroundColor = PrimaryBlue100,
                                contentColor = PrimaryBlue700,
                                size = 36.dp
                            )
                            
                            // Profile Picture
                            Surface(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clickable(onClick = onProfileClick),
                                shape = CircleShape,
                                color = PrimaryBlue500,
                                shadowElevation = 2.dp
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = userName.first().toString(),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Hoş geldin,",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary700
                    )
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary900
                    )
                }
            }
            else -> {
                // Normal ve büyük ekranlar için standard layout
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
                        Spacer(modifier = Modifier.height(config.verticalSpacing(4.dp)))
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
                        horizontalArrangement = Arrangement.spacedBy(config.componentPadding())
                    ) {
                        AnimatedIconButton(
                            icon = Icons.Default.Notifications,
                            onClick = onNotificationClick,
                            backgroundColor = PrimaryBlue100,
                            contentColor = PrimaryBlue700,
                            size = config.adaptiveHeight(
                                extraSmall = 40.dp,
                                compact = 44.dp,
                                medium = 48.dp,
                                expanded = 52.dp
                            )
                        )
                        
                        AnimatedIconButton(
                            icon = Icons.Default.Settings,
                            onClick = onSettingsClick,
                            backgroundColor = HealthGreen100,
                            contentColor = HealthGreen700,
                            size = config.adaptiveHeight(
                                extraSmall = 40.dp,
                                compact = 44.dp,
                                medium = 48.dp,
                                expanded = 52.dp
                            )
                        )
                        
                        // Profile Picture
                        Surface(
                            modifier = Modifier
                                .size(
                                    config.adaptiveHeight(
                                        extraSmall = 44.dp,
                                        compact = 48.dp,
                                        medium = 52.dp,
                                        expanded = 56.dp
                                    )
                                )
                                .clickable(onClick = onProfileClick),
                            shape = CircleShape,
                            color = PrimaryBlue500,
                            shadowElevation = 4.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = userName.first().toString(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = when (config.screenSizeClass) {
                                        ScreenSizeClass.EXTRA_SMALL -> 14.sp
                                        ScreenSizeClass.COMPACT -> 16.sp
                                        ScreenSizeClass.MEDIUM -> 18.sp
                                        ScreenSizeClass.EXPANDED -> 20.sp
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeGlassCard(
    quote: String,
    config: ResponsiveConfig
) {
    GradientGlassCard(
        modifier = Modifier.fillMaxWidth(),
        gradientColors = listOf(
            WellnessTeal400.copy(alpha = 0.12f),
            HealthGreen400.copy(alpha = 0.08f),
            Color.Transparent
        ),
        cornerRadius = config.adaptiveHeight(
            extraSmall = 16.dp,
            compact = 20.dp,
            medium = 24.dp,
            expanded = 28.dp
        )
    ) {
        when (config.screenSizeClass) {
            ScreenSizeClass.EXTRA_SMALL -> {
                // Küçük ekranlar için dikey layout
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        color = WellnessTeal100
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = WellnessTeal700,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(config.verticalSpacing(8.dp)))
                    
                    Text(
                        text = "Günün Motivasyonu",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = WellnessTeal700,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = quote,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextPrimary900,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> {
                // Normal ve büyük ekranlar için yatay layout
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(
                            config.adaptiveHeight(
                                extraSmall = 48.dp,
                                compact = 56.dp,
                                medium = 64.dp,
                                expanded = 72.dp
                            )
                        ),
                        shape = CircleShape,
                        color = WellnessTeal100
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = WellnessTeal700,
                            modifier = Modifier.padding(config.componentPadding(12.dp))
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(config.componentPadding()))
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Günün Motivasyonu",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = WellnessTeal700
                        )
                        Spacer(modifier = Modifier.height(config.verticalSpacing(4.dp)))
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
    }
}

@Composable
fun ProgressSummarySection(
    completionPercentage: Float,
    completedExercises: Int,
    totalExercises: Int,
    streakDays: Int,
    totalPoints: Int,
    config: ResponsiveConfig
) {
    Column(modifier = Modifier) {
        // Main Progress Card
        AnimatedProgressCard(
            title = "Bugünkü İlerleme",
            progress = completionPercentage,
            unit = "%",
            icon = Icons.Default.TrendingUp,
            progressColor = HealthGreen500,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(config.verticalSpacing(16.dp)))
        
        // Stats Grid - Responsive layout
        ResponsiveStatsGrid(
            items = listOf(
                {
                    StatCard(
                        title = "Tamamlanan",
                        value = "$completedExercises/$totalExercises",
                        subtitle = "egzersiz",
                        icon = Icons.Default.CheckCircle,
                        color = HealthGreen500,
                        config = config
                    )
                },
                {
                    StatCard(
                        title = "Seri",
                        value = "$streakDays",
                        subtitle = "gün",
                        icon = Icons.Default.LocalFireDepartment,
                        color = WarningOrange,
                        config = config
                    )
                },
                {
                    StatCard(
                        title = "Puan",
                        value = "$totalPoints",
                        subtitle = "toplam",
                        icon = Icons.Default.Stars,
                        color = PrimaryBlue500,
                        config = config
                    )
                }
            ),
            config = config
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    config: ResponsiveConfig
) {
    GradientGlassCard(
        modifier = Modifier.fillMaxWidth(),
        gradientColors = listOf(
            color.copy(alpha = 0.1f),
            color.copy(alpha = 0.05f),
            Color.Transparent
        ),
        cornerRadius = config.adaptiveHeight(
            extraSmall = 12.dp,
            compact = 16.dp,
            medium = 20.dp,
            expanded = 24.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(config.verticalSpacing(4.dp))
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(
                    config.adaptiveHeight(
                        extraSmall = 20.dp,
                        compact = 24.dp,
                        medium = 28.dp,
                        expanded = 32.dp
                    )
                )
            )
            
            Text(
                text = value,
                style = when (config.screenSizeClass) {
                    ScreenSizeClass.EXTRA_SMALL -> MaterialTheme.typography.titleMedium
                    else -> MaterialTheme.typography.headlineSmall
                },
                fontWeight = FontWeight.Bold,
                color = TextPrimary900,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary700,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = color,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun QuickActionsSection(
    navController: NavController,
    config: ResponsiveConfig
) {
    Column(modifier = Modifier) {
        Text(
            text = "Hızlı Eylemler",
            style = when (config.screenSizeClass) {
                ScreenSizeClass.EXTRA_SMALL -> MaterialTheme.typography.titleMedium
                else -> MaterialTheme.typography.titleLarge
            },
            fontWeight = FontWeight.Bold,
            color = TextPrimary900,
            modifier = Modifier.padding(bottom = config.verticalSpacing())
        )
        
        when (config.screenSizeClass) {
            ScreenSizeClass.EXTRA_SMALL, ScreenSizeClass.COMPACT -> {
                // Küçük ekranlarda horizontal scroll
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(config.componentPadding()),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    item {
                        ModernActionCard(
                            title = "Egzersize Başla",
                            subtitle = "Günlük programını tamamla",
                            icon = Icons.Default.PlayArrow,
                            colors = listOf(HealthGreen400, HealthGreen600),
                            onClick = { navController.navigate(Screen.ExerciseList.route) },
                            config = config
                        )
                    }
                    
                    item {
                        ModernActionCard(
                            title = "İlerleme",
                            subtitle = "Geçmiş performansını gör",
                            icon = Icons.Default.Timeline,
                            colors = listOf(PrimaryBlue400, PrimaryBlue600),
                            onClick = { navController.navigate(Screen.ExerciseCalendar.route) },
                            config = config
                        )
                    }
                    
                    item {
                        ModernActionCard(
                            title = "Sıralama",
                            subtitle = "Liderlik tablosunu incele",
                            icon = Icons.Default.EmojiEvents,
                            colors = listOf(WarningOrange, Color(0xFFF57C00)),
                            onClick = { navController.navigate(Screen.Leaderboard.route) },
                            config = config
                        )
                    }
                }
            }
            else -> {
                // Büyük ekranlarda grid
                ResponsiveGrid(
                    items = listOf(
                        {
                            ModernActionCard(
                                title = "Egzersize Başla",
                                subtitle = "Günlük programını tamamla",
                                icon = Icons.Default.PlayArrow,
                                colors = listOf(HealthGreen400, HealthGreen600),
                                onClick = { navController.navigate(Screen.ExerciseList.route) },
                                config = config
                            )
                        },
                        {
                            ModernActionCard(
                                title = "İlerleme",
                                subtitle = "Geçmiş performansını gör",
                                icon = Icons.Default.Timeline,
                                colors = listOf(PrimaryBlue400, PrimaryBlue600),
                                onClick = { navController.navigate(Screen.ExerciseCalendar.route) },
                                config = config
                            )
                        },
                        {
                            ModernActionCard(
                                title = "Sıralama",
                                subtitle = "Liderlik tablosunu incele",
                                icon = Icons.Default.EmojiEvents,
                                colors = listOf(WarningOrange, Color(0xFFF57C00)),
                                onClick = { navController.navigate(Screen.Leaderboard.route) },
                                config = config
                            )
                        }
                    ),
                    minItemWidth = 180.dp,
                    config = config
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
    modifier: Modifier = Modifier,
    config: ResponsiveConfig
) {
    val cardWidth = when (config.screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> 160.dp
        ScreenSizeClass.COMPACT -> 180.dp
        ScreenSizeClass.MEDIUM -> 200.dp
        ScreenSizeClass.EXPANDED -> 220.dp
    }
    
    val cardHeight = config.adaptiveHeight(
        extraSmall = 100.dp,
        compact = 120.dp,
        medium = 140.dp,
        expanded = 160.dp
    )

    Surface(
        modifier = modifier
            .width(cardWidth)
            .height(cardHeight),
        shape = RoundedCornerShape(
            config.adaptiveHeight(
                extraSmall = 16.dp,
                compact = 20.dp,
                medium = 24.dp,
                expanded = 28.dp
            )
        ),
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
                .padding(config.componentPadding())
        ) {
            Column {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(
                        config.adaptiveHeight(
                            extraSmall = 24.dp,
                            compact = 28.dp,
                            medium = 32.dp,
                            expanded = 36.dp
                        )
                    )
                )
                
                Spacer(modifier = Modifier.height(config.verticalSpacing(8.dp)))
                
                Text(
                    text = title,
                    style = when (config.screenSizeClass) {
                        ScreenSizeClass.EXTRA_SMALL -> MaterialTheme.typography.titleSmall
                        else -> MaterialTheme.typography.titleMedium
                    },
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
    config: ResponsiveConfig
) {
    GradientGlassCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(Screen.ExerciseList.route) },
        gradientColors = listOf(
            PrimaryBlue400.copy(alpha = 0.12f),
            WellnessTeal400.copy(alpha = 0.08f),
            Color.Transparent
        ),
        cornerRadius = config.adaptiveHeight(
            extraSmall = 16.dp,
            compact = 20.dp,
            medium = 24.dp,
            expanded = 28.dp
        )
    ) {
        when (config.screenSizeClass) {
            ScreenSizeClass.EXTRA_SMALL -> {
                // Küçük ekranlar için dikey layout
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(64.dp),
                        shape = CircleShape,
                        color = HealthGreen100
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = HealthGreen600,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(config.verticalSpacing()))
                    
                    Text(
                        text = "Bugünkü Egzersizler",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary900,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "$todaysExercises egzersiz hazır",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary700,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(config.verticalSpacing()))
                    
                    GradientButton(
                        text = "Başla",
                        onClick = { navController.navigate(Screen.ExerciseList.route) },
                        gradientColors = listOf(HealthGreen400, HealthGreen600),
                        leadingIcon = Icons.Default.PlayArrow,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            else -> {
                // Normal ve büyük ekranlar için yatay layout
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
                        
                        Spacer(modifier = Modifier.height(config.verticalSpacing()))
                        
                        GradientButton(
                            text = "Başla",
                            onClick = { navController.navigate(Screen.ExerciseList.route) },
                            gradientColors = listOf(HealthGreen400, HealthGreen600),
                            leadingIcon = Icons.Default.PlayArrow,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(config.componentPadding()))
                    
                    Surface(
                        modifier = Modifier.size(
                            config.adaptiveHeight(
                                extraSmall = 64.dp,
                                compact = 72.dp,
                                medium = 80.dp,
                                expanded = 88.dp
                            )
                        ),
                        shape = CircleShape,
                        color = HealthGreen100
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = HealthGreen600,
                            modifier = Modifier.padding(config.componentPadding(16.dp))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FloatingEmergencyButton(
    onClick: () -> Unit,
    config: ResponsiveConfig,
    modifier: Modifier = Modifier
) {
    val buttonSize = config.adaptiveHeight(
        extraSmall = 56.dp,
        compact = 64.dp,
        medium = 72.dp,
        expanded = 80.dp
    )
    
    PulseFloatingActionButton(
        icon = Icons.Default.Phone,
        onClick = onClick,
        backgroundColor = ModernErrorRed,
        contentColor = Color.White,
        size = buttonSize,
        isPulsing = true,
        modifier = modifier
    )
} 