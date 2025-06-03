package com.example.fiziktedaviapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat

// Modern dark theme color scheme using Material You colors
private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary
)

// Modern light theme color scheme using Material You colors
private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary
)

// Eski renk adlarını yeni Material You renkleriyle eşleştirme
// Ana renkler
val Primary = md_theme_light_primary           // 0xFF0056D2 - Koyu Mavi
val PrimaryLight = md_theme_light_primaryContainer  // 0xFFD4E3FF - Açık Mavi
val PrimaryDark = md_theme_dark_primary        // 0xFFA6C8FF - Parlak Mavi

// İkincil renkler
val Secondary = md_theme_light_secondary       // 0xFF006E1D - Koyu Yeşil
val SecondaryLight = md_theme_light_secondaryContainer // 0xFFB8F397 - Açık Yeşil
val SecondaryDark = md_theme_dark_secondary    // 0xFF9DD67E - Parlak Yeşil

// Yüzey ve arka plan renkleri
val Surface = md_theme_light_surface          // 0xFFFEFBFF - Beyaz
val SurfaceLight = md_theme_light_background   // 0xFFF3F7FF - Açık Mavi Beyaz
val SurfaceDark = md_theme_dark_surface       // 0xFF121417 - Koyu Siyah
val Background = md_theme_light_background    // 0xFFF3F7FF - Açık Mavi Beyaz
val BackgroundLight = md_theme_light_surface   // 0xFFFEFBFF - Beyaz
val BackgroundDark = md_theme_dark_background  // 0xFF1A1C22 - Koyu Siyah

// Hata ve uyarı renkleri
val Error = md_theme_light_error             // 0xFFBA1A1A - Kırmızı

// Metin renkleri
val TextPrimary = md_theme_light_onSurface    // 0xFF1A1C22 - Koyu Siyah
val TextSecondary = md_theme_light_onSurfaceVariant // 0xFF42474E - Koyu Gri
val TextHint = md_theme_light_outline         // 0xFF737983 - Orta Gri

// Renk sistemi uyumluluğu için eski adlandırmalar
val DarkBlue = md_theme_light_primary         // 0xFF0056D2 - Koyu Mavi
val LightBlue = md_theme_light_primaryContainer  // 0xFFD4E3FF - Açık Mavi
val MediumBlue = md_theme_light_tertiary      // 0xFF00629D - Koyu Turkuaz

// Yeşil tonları
val DarkGreen = md_theme_light_secondary      // 0xFF006E1D - Koyu Yeşil
val MediumGreen = md_theme_light_secondary.copy(alpha = 0.8f) // Orta Yeşil
val LightGreen = md_theme_light_secondaryContainer // 0xFFB8F397 - Açık Yeşil

// Uyarı renkleri
val WarningYellow = WarningOrange             // 0xFFFF9800 - Turuncu
val ErrorRed = md_theme_light_error           // 0xFFBA1A1A - Kırmızı

@Composable
fun FizikTedaviAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Use system dark theme setting by default
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, // Enable dynamic color by default on Android 12+
    content: @Composable () -> Unit
) {
    // Get screen metrics for responsive design adjustments
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isTablet = screenWidth > 600.dp
    
    // Determine font and padding scale factors based on screen size
    val fontScale = when {
        screenWidth > 840.dp -> 1.15f  // Larger tablets
        screenWidth > 600.dp -> 1.1f   // Tablets
        screenWidth > 400.dp -> 1.0f   // Standard phones
        else -> 0.95f                  // Small phones
    }
    
    val paddingScale = when {
        screenWidth > 840.dp -> 1.2f
        screenWidth > 600.dp -> 1.1f
        else -> 1.0f
    }
    
    // Apply Material You dynamic color scheme when available, otherwise use our custom theme
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    // Apply status bar styling
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Make status bar color either transparent or use surface color for a more immersive experience
            window.statusBarColor = colorScheme.surface.toArgb()
            // Use appropriate light/dark status bar icons based on theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    
    // Provide custom density to scale UI elements based on screen size
    CompositionLocalProvider(
        LocalDensity provides Density(
            density = LocalDensity.current.density,
            fontScale = LocalDensity.current.fontScale * fontScale
        )
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes, // Using our custom shapes
            content = content
        )
    }
}