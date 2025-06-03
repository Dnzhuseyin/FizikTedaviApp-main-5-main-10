package com.example.fiziktedaviapp.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.model.Exercise
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCalendarScreen(navController: NavController) {
    // Şu anki ay
    val calendar = remember { Calendar.getInstance() }
    var currentMonth by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var currentYear by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    
    // Egzersiz durumları (gerçek uygulamada veritabanından alınacak)
    val exerciseStatus = remember {
        val daysInMonth = getDaysInMonth(currentYear, currentMonth)
        val status = mutableMapOf<Int, ExerciseStatus>()
        
        // Örnek veriler
        for (i in 1..daysInMonth) {
            status[i] = when {
                i % 5 == 0 -> ExerciseStatus.NOT_DONE
                i % 3 == 0 -> ExerciseStatus.PARTIAL
                i % 2 == 0 -> ExerciseStatus.DONE
                else -> ExerciseStatus.NOT_SCHEDULED
            }
        }
        
        status
    }
    
    // Seçili gün
    var selectedDay by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }
    
    // Seçili gündeki egzersizler (gerçek uygulamada veritabanından alınacak)
    val selectedDayExercises = remember(selectedDay) {
        listOf(
            Exercise(
                id = "1",
                name = "Diz Bükme Germe",
                bodyPart = "Diz",
                duration = "5 dakika",
                repetitions = "3 set, 10 tekrar"
            ),
            Exercise(
                id = "2",
                name = "Bel Rotasyon Egzersizi",
                bodyPart = "Bel",
                duration = "8 dakika",
                repetitions = "2 set, 15 tekrar"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Egzersiz Takibi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = SurfaceLight,
                contentColor = DarkBlue
            ) {
                NavigationBar(
                    containerColor = SurfaceLight,
                    contentColor = DarkBlue
                ) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Dashboard.route) { inclusive = true }
                        } },
                        icon = { Icon(Icons.Outlined.Home, contentDescription = "Ana Sayfa") },
                        label = { Text("Ana Sayfa") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.ExerciseList.route) },
                        icon = { Icon(Icons.Outlined.FitnessCenter, contentDescription = "Egzersizler") },
                        label = { Text("Egzersizler") }
                    )
                    
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Zaten bu ekrandayız */ },
                        icon = { Icon(Icons.Filled.CalendarMonth, contentDescription = "Takvim") },
                        label = { Text("Takvim") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.Profile.route) },
                        icon = { Icon(Icons.Outlined.Person, contentDescription = "Profil") },
                        label = { Text("Profil") }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundLight)
        ) {
            // Ay ve yıl seçici
            MonthYearSelector(
                currentMonth = currentMonth,
                currentYear = currentYear,
                onPreviousMonth = {
                    if (currentMonth == 0) {
                        currentMonth = 11
                        currentYear--
                    } else {
                        currentMonth--
                    }
                },
                onNextMonth = {
                    if (currentMonth == 11) {
                        currentMonth = 0
                        currentYear++
                    } else {
                        currentMonth++
                    }
                }
            )
            
            // Haftanın günleri başlığı
            WeekdaysHeader()
            
            // Takvim ızgarası
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                // Ayın ilk gününün hafta içi pozisyonuna göre boşluk ekle
                val firstDayOffset = getFirstDayOfMonthOffset(currentYear, currentMonth)
                items(firstDayOffset) {
                    Box(modifier = Modifier.padding(4.dp))
                }
                
                // Ayın günleri
                val daysInMonth = getDaysInMonth(currentYear, currentMonth)
                items(daysInMonth) { day ->
                    val dayNumber = day + 1
                    val status = exerciseStatus[dayNumber] ?: ExerciseStatus.NOT_SCHEDULED
                    
                    CalendarDay(
                        day = dayNumber,
                        status = status,
                        isSelected = dayNumber == selectedDay,
                        onClick = { selectedDay = dayNumber }
                    )
                }
            }
            
            // Seçilen gün için egzersiz listesi
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
                    // Seçilen tarih başlığı
                    Text(
                        text = formatDate(currentYear, currentMonth, selectedDay),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    if (selectedDayExercises.isEmpty()) {
                        // Egzersiz yoksa
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bu gün için planlanmış egzersiz bulunmamaktadır.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextSecondary,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        // Egzersiz listesi
                        Text(
                            text = "Günün Egzersizleri",
                            style = MaterialTheme.typography.titleMedium,
                            color = DarkBlue,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(selectedDayExercises) { exercise ->
                                CalendarExerciseItem(
                                    exercise = exercise,
                                    onClick = { navController.navigate(Screen.ExerciseDetail.createRoute(exercise.id)) }
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
fun MonthYearSelector(
    currentMonth: Int,
    currentYear: Int,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Önceki Ay",
                tint = DarkBlue
            )
        }
        
        Text(
            text = getMonthYearText(currentMonth, currentYear),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        
        IconButton(onClick = onNextMonth) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Sonraki Ay",
                tint = DarkBlue
            )
        }
    }
}

@Composable
fun WeekdaysHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val weekdays = listOf("Pt", "Sa", "Ça", "Pe", "Cu", "Ct", "Pz")
        weekdays.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    day: Int,
    status: ExerciseStatus,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(
                when {
                    isSelected -> MediumBlue
                    else -> Color.Transparent
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else TextPrimary
        )
        
        // Durum indikatörü
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 2.dp)
                .size(8.dp)
                .clip(CircleShape)
                .background(
                    when (status) {
                        ExerciseStatus.DONE -> MediumGreen
                        ExerciseStatus.PARTIAL -> WarningYellow
                        ExerciseStatus.NOT_DONE -> ErrorRed
                        ExerciseStatus.NOT_SCHEDULED -> Color.Transparent
                    }
                )
        )
    }
}

@Composable
fun CalendarExerciseItem(
    exercise: Exercise,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.FitnessCenter,
                contentDescription = null,
                tint = DarkBlue,
                modifier = Modifier.size(24.dp)
            )
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = exercise.bodyPart,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "Süre",
                        tint = MediumBlue,
                        modifier = Modifier.size(12.dp)
                    )
                    
                    Text(
                        text = exercise.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Detay",
                tint = MediumBlue
            )
        }
    }
}

// Yardımcı fonksiyonlar
fun getMonthYearText(month: Int, year: Int): String {
    val monthNames = listOf(
        "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
        "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
    )
    return "${monthNames[month]} $year"
}

fun getDaysInMonth(year: Int, month: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun getFirstDayOfMonthOffset(year: Int, month: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    // Calendar.SUNDAY = 1, Calendar.MONDAY = 2, ... biz Pazartesi'yi 0 olarak istiyoruz
    var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2
    if (dayOfWeek < 0) dayOfWeek = 6 // Pazar günü için düzeltme
    return dayOfWeek
}

fun formatDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateFormat = SimpleDateFormat("d MMMM yyyy, EEEE", Locale("tr"))
    return dateFormat.format(calendar.time)
}

// Egzersiz durumu enum sınıfı
enum class ExerciseStatus {
    DONE,        // Yapıldı
    PARTIAL,     // Kısmen yapıldı
    NOT_DONE,    // Yapılmadı
    NOT_SCHEDULED // Planlanmamış
} 