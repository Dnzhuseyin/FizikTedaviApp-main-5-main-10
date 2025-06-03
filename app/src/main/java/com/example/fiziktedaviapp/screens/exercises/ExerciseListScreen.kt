package com.example.fiziktedaviapp.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.model.Exercise
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Tümü") }
    
    // Filtreleme seçenekleri
    val filterOptions = listOf("Tümü", "Bel", "Boyun", "Diz", "Omuz", "Kalça")
    
    // Örnek egzersiz listesi (daha sonra model sınıfından alınacak)
    val exercises = remember {
        listOf(
            Exercise(
                id = "1",
                name = "Diz Bükme Germe",
                bodyPart = "Diz",
                duration = "5 dakika",
                imageUrl = null
            ),
            Exercise(
                id = "2",
                name = "Bel Rotasyon Egzersizi",
                bodyPart = "Bel",
                duration = "8 dakika",
                imageUrl = null
            ),
            Exercise(
                id = "3",
                name = "Boyun Esnetme",
                bodyPart = "Boyun",
                duration = "3 dakika",
                imageUrl = null
            ),
            Exercise(
                id = "4",
                name = "Omuz Çevirme",
                bodyPart = "Omuz",
                duration = "6 dakika",
                imageUrl = null
            ),
            Exercise(
                id = "5",
                name = "Kalça Abdüksiyon",
                bodyPart = "Kalça",
                duration = "7 dakika",
                imageUrl = null
            ),
            Exercise(
                id = "6",
                name = "Diz Güçlendirme",
                bodyPart = "Diz",
                duration = "10 dakika",
                imageUrl = null
            ),
        )
    }
    
    // Filtreleme işlemi
    val filteredExercises = exercises.filter {
        (it.name.contains(searchQuery, ignoreCase = true) || 
                it.bodyPart.contains(searchQuery, ignoreCase = true)) &&
                (selectedFilter == "Tümü" || it.bodyPart == selectedFilter)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Egzersizler") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { /* TODO: Favori egzersizleri göster */ }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favoriler",
                            tint = Color.White
                        )
                    }
                }
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
                        selected = true,
                        onClick = { /* Zaten bu ekrandayız */ },
                        icon = { Icon(Icons.Filled.FitnessCenter, contentDescription = "Egzersizler") },
                        label = { Text("Egzersizler") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.ExerciseCalendar.route) },
                        icon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = "Takvim") },
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
            // Arama çubuğu
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Egzersiz ara...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ara") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Temizle")
                        }
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceLight,
                    unfocusedContainerColor = SurfaceLight,
                    focusedIndicatorColor = DarkBlue,
                    unfocusedIndicatorColor = LightBlue
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            
            // Filtre seçimi
            androidx.compose.foundation.lazy.LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterOptions) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MediumBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
            
            // Egzersiz listesi
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredExercises) { exercise ->
                    ExerciseCard(
                        exercise = exercise,
                        onClick = { navController.navigate(Screen.ExerciseDetail.createRoute(exercise.id)) }
                    )
                }
                
                // Alt boşluk
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Egzersiz ikonu veya resmi
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightBlue),
                contentAlignment = Alignment.Center
            ) {
                // Gerçek resim yerine geçici ikon
                val icon = when (exercise.bodyPart) {
                    "Diz" -> Icons.Outlined.Label
                    "Bel" -> Icons.Outlined.Label
                    "Boyun" -> Icons.Outlined.Label
                    "Omuz" -> Icons.Outlined.Label
                    "Kalça" -> Icons.Outlined.Label
                    else -> Icons.Outlined.FitnessCenter
                }
                
                Icon(
                    imageVector = icon,
                    contentDescription = exercise.bodyPart,
                    tint = DarkBlue,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            // Egzersiz detayları
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                
                Text(
                    text = exercise.bodyPart,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "Süre",
                        tint = MediumBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Text(
                        text = exercise.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            
            // Başla butonu
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MediumGreen
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = "Başla", fontSize = 14.sp)
            }
        }
    }
} 