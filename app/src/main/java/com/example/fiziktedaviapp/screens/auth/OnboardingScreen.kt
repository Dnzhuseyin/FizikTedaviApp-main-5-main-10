package com.example.fiziktedaviapp.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.DarkBlue
import com.example.fiziktedaviapp.ui.theme.SurfaceLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    // Seçilen kullanım nedeni
    val reasonOptions = listOf("Ameliyat sonrası iyileşme", "Sporcu yaralanması", "Genel sağlık")
    var selectedReason by remember { mutableStateOf(reasonOptions[0]) }
    
    // Fizyoterapist ile çalışıyor mu
    var hasTherapist by remember { mutableStateOf(false) }
    
    // Ameliyat geçirdi mi
    var hadSurgery by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
    ) {
        // Geri butonu
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Geri",
                tint = DarkBlue
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 56.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Bize kendinizden bahsedin",
                style = MaterialTheme.typography.headlineMedium,
                color = DarkBlue,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // "Bu uygulamayı neden kullanıyorsunuz?" sorusu
            Text(
                text = "Bu uygulamayı neden kullanıyorsunuz?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Column(modifier = Modifier.selectableGroup()) {
                reasonOptions.forEach { reason ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (reason == selectedReason),
                                onClick = { selectedReason = reason },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (reason == selectedReason),
                            onClick = null // null because we're handling the click on the row
                        )
                        Text(
                            text = reason,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // "Bir fizyoterapist ile çalışıyor musunuz?" sorusu
            Text(
                text = "Bir fizyoterapist ile çalışıyor musunuz?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = hasTherapist,
                    onCheckedChange = { hasTherapist = it }
                )
                Text(
                    text = if (hasTherapist) "Evet" else "Hayır",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // "Yakın zamanda ameliyat geçirdiniz mi?" sorusu
            Text(
                text = "Yakın zamanda ameliyat geçirdiniz mi?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = hadSurgery,
                    onCheckedChange = { hadSurgery = it }
                )
                Text(
                    text = if (hadSurgery) "Evet" else "Hayır",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Devam et butonu
            Button(
                onClick = { navController.navigate(Screen.Dashboard.route) },
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Devam Et", fontSize = 16.sp)
            }
        }
    }
} 