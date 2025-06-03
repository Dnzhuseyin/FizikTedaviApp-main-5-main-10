package com.example.fiziktedaviapp.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: LocalDateTime,
    val isRead: Boolean,
    val type: NotificationType
)

enum class NotificationType {
    EXERCISE, APPOINTMENT, SYSTEM, ACHIEVEMENT
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
    val notifications = remember {
        generateSampleNotifications()
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bildirimler") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Geri",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Tüm bildirimleri okundu olarak işaretle */ }) {
                        Icon(
                            Icons.Filled.CheckCircle,
                            contentDescription = "Tümünü Okundu İşaretle",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (notifications.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Henüz bildiriminiz yok",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notifications) { notification ->
                    NotificationItem(notification)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationItem(notification: Notification) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (!notification.isRead) 
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
            else 
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (!notification.isRead) 2.dp else 1.dp
        ),
        onClick = { /* Mark as read */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Notification icon based on type
            val icon = when (notification.type) {
                NotificationType.EXERCISE -> Icons.Filled.DateRange
                NotificationType.APPOINTMENT -> Icons.Filled.DateRange
                NotificationType.SYSTEM -> Icons.Filled.Notifications
                NotificationType.ACHIEVEMENT -> Icons.Filled.CheckCircle
            }
            
            val iconTint = when (notification.type) {
                NotificationType.EXERCISE -> MaterialTheme.colorScheme.primary
                NotificationType.APPOINTMENT -> MaterialTheme.colorScheme.tertiary
                NotificationType.SYSTEM -> MaterialTheme.colorScheme.secondary
                NotificationType.ACHIEVEMENT -> MaterialTheme.colorScheme.tertiary
            }
            
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Format date for display
                val formatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm")
                Text(
                    text = notification.timestamp.format(formatter),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
            
            if (!notification.isRead) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(start = 8.dp)
                        .align(Alignment.Top)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}

private fun generateSampleNotifications(): List<Notification> {
    val now = LocalDateTime.now()
    
    return listOf(
        Notification(
            id = "1",
            title = "Yeni egzersiz planınız hazır",
            message = "Terapistiniz yeni bir egzersiz planı oluşturdu. İncelemek için tıklayın.",
            timestamp = now.minusHours(2),
            isRead = false,
            type = NotificationType.EXERCISE
        ),
        Notification(
            id = "2",
            title = "Bugünkü hedefleriniz",
            message = "Bugün 3 egzersiz rutininiz var. Sağlıklı bir geleceğe bir adım daha!",
            timestamp = now.minusDays(1),
            isRead = true,
            type = NotificationType.EXERCISE
        ),
        Notification(
            id = "3",
            title = "Randevunuz hatırlatma",
            message = "Yarın saat 14:00'da Dr. Ayşe Yılmaz ile randevunuz var.",
            timestamp = now.minusDays(2),
            isRead = true,
            type = NotificationType.APPOINTMENT
        ),
        Notification(
            id = "4",
            title = "Tebrikler!",
            message = "7 günlük egzersiz serinizi tamamladınız! Harika gidiyorsunuz!",
            timestamp = now.minusDays(3),
            isRead = true,
            type = NotificationType.ACHIEVEMENT
        ),
        Notification(
            id = "5",
            title = "Yeni özellik eklendi",
            message = "Artık egzersiz videolarını kaydedebilirsiniz. Terapistiniz tekniğinizi değerlendirebilecek.",
            timestamp = now.minusDays(5),
            isRead = true,
            type = NotificationType.SYSTEM
        )
    )
}
