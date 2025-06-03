package com.example.fiziktedaviapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fiziktedaviapp.components.BottomNavigationBar
import com.example.fiziktedaviapp.navigation.NavGraph
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.FizikTedaviAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FizikTedaviAppTheme {
                FizikTedaviApp()
            }
        }
    }
}

@Composable
fun FizikTedaviApp() {
    val navController = rememberNavController()
    val bottomBarVisible = rememberSaveable { mutableStateOf(true) }
    
    // Get current navigation state
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Routes where bottom bar should be hidden
    val routesToHideBottomBar = listOf(
        Screen.Login.route,
        Screen.Register.route,
        Screen.ForgotPassword.route,
        Screen.Onboarding.route,
        Screen.ExerciseDetail.route, // This is a pattern route, needs special handling
        "exercise_detail/", // Beginning of parameterized route
        Screen.TherapistCall.route, // Terapist arama ekranı
        Screen.Settings.route // Ayarlar ekranı
    )
    
    // Check if bottom navigation should be visible based on current route
    bottomBarVisible.value = currentRoute?.let { route ->
        !routesToHideBottomBar.any { route.contains(it) }
    } ?: false
    
    // Use the scaffold for consistent layout with bottom navigation
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0), // İnset sorununu düzeltmek için
        containerColor = MaterialTheme.colorScheme.background, // Arka plan rengini tema ile uyumlu yap
        contentColor = MaterialTheme.colorScheme.onBackground, // İçerik rengini tema ile uyumlu yap
        bottomBar = {
            if (bottomBarVisible.value) {
                BottomNavigationBar(
                    navController = navController,
                    visible = true
                )
            }
        }
    ) { innerPadding ->
        // Calculate appropriate padding based on whether bottom bar is shown
        val contentModifier = if (bottomBarVisible.value) {
            // Bottom bar var ise, içeriğin altında boşluk bırakmadan sadece gerekli padding'leri uygula
            Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = innerPadding.calculateBottomPadding()
            )
        } else {
            // Bottom bar yok ise, sadece gerekli padding'leri uygula
            Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp // Alt kısımda biraz padding ekleyelim
            )
        }
        
        NavGraph(
            navController = navController,
            modifier = contentModifier
        )
    }
}