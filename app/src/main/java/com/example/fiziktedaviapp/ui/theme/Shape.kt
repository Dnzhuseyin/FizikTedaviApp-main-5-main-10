package com.example.fiziktedaviapp.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

// Modern Healthcare App Shapes - Larger corner radii for friendly, approachable feel
val Shapes = Shapes(
    // Küçük bileşenler için (chips, tags, small buttons)
    small = RoundedCornerShape(12.dp),
    
    // Kartlar gibi orta boy bileşenler için (cards, buttons)
    medium = RoundedCornerShape(16.dp),
    
    // Modal, dialog gibi büyük bileşenler için (modals, large cards)
    large = RoundedCornerShape(24.dp),
    
    // Extra large bileşenler için (bottom sheet, hero sections)
    extraLarge = RoundedCornerShape(32.dp)
)

// Custom shapes for specific healthcare app components
object HealthcareShapes {
    // Button shapes
    val buttonSmall = RoundedCornerShape(12.dp)
    val buttonMedium = RoundedCornerShape(16.dp)
    val buttonLarge = RoundedCornerShape(20.dp)
    val buttonPill = RoundedCornerShape(50.dp)
    
    // Card shapes
    val cardSmall = RoundedCornerShape(12.dp)
    val cardMedium = RoundedCornerShape(16.dp)
    val cardLarge = RoundedCornerShape(20.dp)
    val cardXLarge = RoundedCornerShape(24.dp)
    val cardHero = RoundedCornerShape(28.dp)
    
    // Surface shapes
    val surfaceSmall = RoundedCornerShape(8.dp)
    val surfaceMedium = RoundedCornerShape(12.dp)
    val surfaceLarge = RoundedCornerShape(16.dp)
    
    // Special shapes
    val circle = CircleShape
    val topRounded = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val bottomRounded = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    val leftRounded = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
    val rightRounded = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
    
    // Progress and indicator shapes
    val progressBar = RoundedCornerShape(8.dp)
    val progressIndicator = RoundedCornerShape(4.dp)
    val badge = RoundedCornerShape(12.dp)
    val tag = RoundedCornerShape(8.dp)
    
    // Container shapes
    val containerSmall = RoundedCornerShape(12.dp)
    val containerMedium = RoundedCornerShape(16.dp)
    val containerLarge = RoundedCornerShape(24.dp)
    val containerXLarge = RoundedCornerShape(32.dp)
    
    // Modal and overlay shapes
    val modalSmall = RoundedCornerShape(16.dp)
    val modalMedium = RoundedCornerShape(20.dp)
    val modalLarge = RoundedCornerShape(24.dp)
    val bottomSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    
    // Input field shapes
    val textFieldSmall = RoundedCornerShape(8.dp)
    val textFieldMedium = RoundedCornerShape(12.dp)
    val textFieldLarge = RoundedCornerShape(16.dp)
    
    // Navigation shapes
    val navigationBar = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    val tabIndicator = RoundedCornerShape(20.dp)
    val floatingActionButton = CircleShape
    
    // Image and media shapes
    val imageSmall = RoundedCornerShape(8.dp)
    val imageMedium = RoundedCornerShape(12.dp)
    val imageLarge = RoundedCornerShape(16.dp)
    val imageXLarge = RoundedCornerShape(20.dp)
    val avatar = CircleShape
    
    // Exercise and health specific shapes
    val exerciseCard = RoundedCornerShape(20.dp)
    val progressChart = RoundedCornerShape(16.dp)
    val healthStatus = RoundedCornerShape(12.dp)
    val emergencyButton = CircleShape
}
