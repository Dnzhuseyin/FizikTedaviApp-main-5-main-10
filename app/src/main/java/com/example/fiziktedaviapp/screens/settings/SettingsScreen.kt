package com.example.fiziktedaviapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    // Ayarlar durumları
    var notificationsEnabled by remember { mutableStateOf(true) }
    var dailyRemindersEnabled by remember { mutableStateOf(true) }
    var darkThemeEnabled by remember { mutableStateOf(false) }
    var hapticFeedbackEnabled by remember { mutableStateOf(true) }
    
    // Uygulama versiyonu
    val appVersion = "1.0.0"
    
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ayarlar") },
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
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Bildirim ayarları
            SettingsSection(title = "Bildirimler") {
                // Bildirimler
                SettingsToggleItem(
                    title = "Bildirimleri Etkinleştir",
                    subtitle = "Uygulama bildirimlerini etkinleştirin veya devre dışı bırakın",
                    icon = Icons.Outlined.Notifications,
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
                
                // Günlük hatırlatıcılar
                SettingsToggleItem(
                    title = "Günlük Hatırlatıcılar",
                    subtitle = "Her gün egzersizlerinizi yapmanız için hatırlatıcı alın",
                    icon = Icons.Outlined.Alarm,
                    checked = dailyRemindersEnabled,
                    onCheckedChange = { dailyRemindersEnabled = it },
                    enabled = notificationsEnabled
                )
                
                // Bildirim saati ayarı
                SettingsActionItem(
                    title = "Hatırlatıcı Saati",
                    subtitle = "Günlük hatırlatıcıların gönderileceği saati seçin",
                    icon = Icons.Outlined.Schedule,
                    onClick = { /* Zaman seçici göster */ },
                    trailingText = "18:00",
                    enabled = notificationsEnabled && dailyRemindersEnabled
                )
            }
            
            // Görünüm ayarları
            SettingsSection(title = "Görünüm") {
                // Karanlık tema
                SettingsToggleItem(
                    title = "Karanlık Tema",
                    subtitle = "Karanlık temayı etkinleştirin",
                    icon = Icons.Outlined.DarkMode,
                    checked = darkThemeEnabled,
                    onCheckedChange = { darkThemeEnabled = it }
                )
                
                // Haptic geri bildirim
                SettingsToggleItem(
                    title = "Dokunmatik Geri Bildirim",
                    subtitle = "Buton tıklamalarında titreşim sağlayın",
                    icon = Icons.Outlined.Vibration,
                    checked = hapticFeedbackEnabled,
                    onCheckedChange = { hapticFeedbackEnabled = it }
                )
            }
            
            // Hesap ayarları
            SettingsSection(title = "Hesap") {
                // Profili düzenle
                SettingsActionItem(
                    title = "Profili Düzenle",
                    subtitle = "Kişisel bilgilerinizi güncelleyin",
                    icon = Icons.Outlined.Edit,
                    onClick = { /* Profil düzenleme ekranına git */ }
                )
                
                // Fizyoterapistim
                SettingsActionItem(
                    title = "Fizyoterapistim",
                    subtitle = "Fizyoterapistinizin iletişim bilgileri",
                    icon = Icons.Outlined.Person,
                    onClick = { /* Fizyoterapist ekranına git */ }
                )
                
                // Çıkış yap
                SettingsActionItem(
                    title = "Çıkış Yap",
                    subtitle = "Hesabınızdan çıkış yapın",
                    icon = Icons.Outlined.Logout,
                    onClick = { 
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    },
                    textColor = ErrorRed
                )
            }
            
            // Destek ve hakkında
            SettingsSection(title = "Destek ve Hakkında") {
                // Yardım ve destek
                SettingsActionItem(
                    title = "Yardım ve Destek",
                    subtitle = "SSS ve destek bilgileri",
                    icon = Icons.Outlined.Help,
                    onClick = { /* Yardım ekranına git */ }
                )
                
                // Kullanım koşulları
                SettingsActionItem(
                    title = "Kullanım Koşulları",
                    subtitle = "Uygulama kullanım koşullarını görüntüleyin",
                    icon = Icons.Outlined.Description,
                    onClick = { /* Kullanım koşulları ekranına git */ }
                )
                
                // Gizlilik politikası
                SettingsActionItem(
                    title = "Gizlilik Politikası",
                    subtitle = "Gizlilik politikamızı görüntüleyin",
                    icon = Icons.Outlined.PrivacyTip,
                    onClick = { /* Gizlilik politikası ekranına git */ }
                )
            }
            
            // Uygulama versiyonu
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Versiyon $appVersion",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = DarkBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = SurfaceLight
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                content()
            }
        }
    }
}

@Composable
fun SettingsToggleItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) DarkBlue else TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier
                .size(24.dp)
                .padding(end = 4.dp)
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = if (enabled) TextPrimary else TextSecondary.copy(alpha = 0.5f),
                fontWeight = FontWeight.SemiBold
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f)
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MediumBlue,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun SettingsActionItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    trailingText: String? = null,
    textColor: Color = TextPrimary,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (enabled) Color.Transparent else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .let { if (enabled) it.clickable(onClick = onClick) else it },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) 
                    if (textColor == ErrorRed) ErrorRed else DarkBlue 
                   else 
                    TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier
                .size(24.dp)
                .padding(end = 4.dp)
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = if (enabled) textColor else TextSecondary.copy(alpha = 0.5f),
                fontWeight = FontWeight.SemiBold
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f)
            )
        }
        
        if (trailingText != null) {
            Text(
                text = trailingText,
                style = MaterialTheme.typography.bodySmall,
                color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f)
            )
        } else {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
} 