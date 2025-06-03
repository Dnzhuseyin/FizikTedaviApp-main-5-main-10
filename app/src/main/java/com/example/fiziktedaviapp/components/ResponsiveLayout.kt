package com.example.fiziktedaviapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn

// Gelişmiş ekran boyutu kategorileri
enum class ScreenSizeClass {
    COMPACT,        // Küçük telefonlar (0-600dp)
    MEDIUM,         // Büyük telefonlar ve küçük tabletler (600-840dp) 
    EXPANDED,       // Tabletler ve daha büyük ekranlar (840dp+)
    EXTRA_SMALL     // Çok küçük telefonlar (0-360dp)
}

// Orientation detection
enum class ScreenOrientation {
    PORTRAIT,
    LANDSCAPE
}

// Responsive configuration
data class ResponsiveConfig(
    val screenSizeClass: ScreenSizeClass,
    val orientation: ScreenOrientation,
    val screenWidth: Dp,
    val screenHeight: Dp,
    val isTablet: Boolean,
    val fontScale: Float,
    val paddingScale: Float,
    val spacingScale: Float
)

// Composition locals
val LocalScreenSizeClass = compositionLocalOf { ScreenSizeClass.COMPACT }
val LocalResponsiveConfig = compositionLocalOf { 
    ResponsiveConfig(
        screenSizeClass = ScreenSizeClass.COMPACT,
        orientation = ScreenOrientation.PORTRAIT,
        screenWidth = 360.dp,
        screenHeight = 640.dp,
        isTablet = false,
        fontScale = 1f,
        paddingScale = 1f,
        spacingScale = 1f
    )
}

/**
 * Gelişmiş ekran boyutu belirleme fonksiyonu
 */
@Composable
fun determineResponsiveConfig(): ResponsiveConfig {
    val configuration = LocalConfiguration.current
    val widthDp = configuration.screenWidthDp.dp
    val heightDp = configuration.screenHeightDp.dp
    
    val screenSizeClass = when {
        widthDp < 360.dp -> ScreenSizeClass.EXTRA_SMALL
        widthDp < 600.dp -> ScreenSizeClass.COMPACT
        widthDp < 840.dp -> ScreenSizeClass.MEDIUM
        else -> ScreenSizeClass.EXPANDED
    }
    
    val orientation = if (widthDp > heightDp) {
        ScreenOrientation.LANDSCAPE
    } else {
        ScreenOrientation.PORTRAIT
    }
    
    val isTablet = widthDp >= 600.dp
    
    // Adaptif ölçekleme faktörleri
    val fontScale = when (screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> 0.85f
        ScreenSizeClass.COMPACT -> 1.0f
        ScreenSizeClass.MEDIUM -> 1.1f
        ScreenSizeClass.EXPANDED -> 1.15f
    }
    
    val paddingScale = when (screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> 0.75f
        ScreenSizeClass.COMPACT -> 1.0f
        ScreenSizeClass.MEDIUM -> 1.2f
        ScreenSizeClass.EXPANDED -> 1.4f
    }
    
    val spacingScale = when (screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> 0.8f
        ScreenSizeClass.COMPACT -> 1.0f
        ScreenSizeClass.MEDIUM -> 1.15f
        ScreenSizeClass.EXPANDED -> 1.3f
    }
    
    return ResponsiveConfig(
        screenSizeClass = screenSizeClass,
        orientation = orientation,
        screenWidth = widthDp,
        screenHeight = heightDp,
        isTablet = isTablet,
        fontScale = fontScale,
        paddingScale = paddingScale,
        spacingScale = spacingScale
    )
}

/**
 * Ana responsive sayfa wrapper'ı
 */
@Composable
fun ResponsivePage(
    modifier: Modifier = Modifier,
    applySystemInsets: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    scrollable: Boolean = true,
    maxWidth: Dp = 1200.dp,
    content: @Composable ColumnScope.(ResponsiveConfig) -> Unit
) {
    val config = determineResponsiveConfig()
    
    // Adaptif padding değerleri
    val horizontalPadding = (16.dp * config.paddingScale).coerceAtLeast(8.dp)
    val verticalPadding = (8.dp * config.paddingScale).coerceAtLeast(4.dp)
    
    // System insets
    val statusBarPadding = if (applySystemInsets) {
        WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    } else 0.dp
    
    val navigationBarPadding = if (applySystemInsets) {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    } else 0.dp
    
    // Font scaling için custom density
    val scaledDensity = Density(
        density = LocalDensity.current.density,
        fontScale = LocalDensity.current.fontScale * config.fontScale
    )
    
    CompositionLocalProvider(
        LocalScreenSizeClass provides config.screenSizeClass,
        LocalResponsiveConfig provides config,
        LocalDensity provides scaledDensity
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            // Content container with max width constraint for large screens
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .then(
                            if (config.screenSizeClass == ScreenSizeClass.EXPANDED) {
                                Modifier.widthIn(max = maxWidth)
                            } else {
                                Modifier.fillMaxWidth()
                            }
                        )
                ) {
                    if (scrollable) {
                        val scrollState = rememberScrollState()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = horizontalPadding,
                                    end = horizontalPadding,
                                    top = verticalPadding + statusBarPadding,
                                    bottom = verticalPadding + navigationBarPadding
                                )
                                .verticalScroll(scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            content(config)
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = horizontalPadding,
                                    end = horizontalPadding,
                                    top = verticalPadding + statusBarPadding,
                                    bottom = verticalPadding + navigationBarPadding
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            content(config)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Adaptif grid sistemi
 */
@Composable
fun ResponsiveGrid(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    minItemWidth: Dp = 280.dp,
    spacing: Dp = 16.dp,
    config: ResponsiveConfig = LocalResponsiveConfig.current
) {
    val scaledSpacing = (spacing * config.spacingScale).coerceAtLeast(8.dp)
    val scaledMinWidth = minItemWidth * config.paddingScale
    
    // Dinamik sütun sayısı hesapla
    val columns = maxOf(1, (config.screenWidth / (scaledMinWidth + scaledSpacing)).toInt())
    
    // Grid düzeni
    val chunkedItems = items.chunked(columns)
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(scaledSpacing)
    ) {
        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(scaledSpacing)
            ) {
                rowItems.forEach { item ->
                    Box(modifier = Modifier.weight(1f)) {
                        item()
                    }
                }
                
                // Eksik hücreleri doldur
                repeat(columns - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * Adaptif layout - Küçük ekranlarda dikey, büyük ekranlarda yatay
 */
@Composable
fun AdaptiveLayout(
    modifier: Modifier = Modifier,
    config: ResponsiveConfig = LocalResponsiveConfig.current,
    spacing: Dp = 16.dp,
    firstContent: @Composable BoxScope.() -> Unit,
    secondContent: @Composable BoxScope.() -> Unit
) {
    val scaledSpacing = (spacing * config.spacingScale).coerceAtLeast(8.dp)
    
    when (config.screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL, ScreenSizeClass.COMPACT -> {
            // Küçük ekranlarda dikey düzen
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(scaledSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    firstContent()
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    secondContent()
                }
            }
        }
        else -> {
            // Büyük ekranlarda yatay düzen
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(scaledSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    firstContent()
                }
                Box(modifier = Modifier.weight(1f)) {
                    secondContent()
                }
            }
        }
    }
}

/**
 * Stats grid için özel responsive düzen
 */
@Composable
fun ResponsiveStatsGrid(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    config: ResponsiveConfig = LocalResponsiveConfig.current
) {
    val columns = when (config.screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> 1
        ScreenSizeClass.COMPACT -> if (config.orientation == ScreenOrientation.LANDSCAPE) 3 else 2
        ScreenSizeClass.MEDIUM -> 3
        ScreenSizeClass.EXPANDED -> 4
    }
    
    val spacing = (12.dp * config.spacingScale).coerceAtLeast(8.dp)
    val chunkedItems = items.chunked(columns)
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                rowItems.forEach { item ->
                    Box(modifier = Modifier.weight(1f)) {
                        item()
                    }
                }
                
                repeat(columns - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * Responsive spacing helper'ları
 */
@Composable
fun ResponsiveConfig.verticalSpacing(base: Dp = 16.dp): Dp {
    return (base * this.spacingScale).coerceAtLeast(8.dp)
}

@Composable
fun ResponsiveConfig.horizontalSpacing(base: Dp = 16.dp): Dp {
    return (base * this.spacingScale).coerceAtLeast(8.dp)
}

@Composable
fun ResponsiveConfig.componentPadding(base: Dp = 16.dp): Dp {
    return (base * this.paddingScale).coerceAtLeast(8.dp)
}

/**
 * Responsive height helper
 */
@Composable
fun ResponsiveConfig.adaptiveHeight(
    extraSmall: Dp,
    compact: Dp,
    medium: Dp,
    expanded: Dp
): Dp {
    return when (this.screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL -> extraSmall
        ScreenSizeClass.COMPACT -> compact
        ScreenSizeClass.MEDIUM -> medium
        ScreenSizeClass.EXPANDED -> expanded
    }
}

/**
 * Quick Actions için responsive layout
 */
@Composable
fun ResponsiveQuickActions(
    modifier: Modifier = Modifier,
    actions: List<@Composable () -> Unit>,
    config: ResponsiveConfig = LocalResponsiveConfig.current
) {
    when (config.screenSizeClass) {
        ScreenSizeClass.EXTRA_SMALL, ScreenSizeClass.COMPACT -> {
            // Küçük ekranlarda scroll edilebilir horizontal layout
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(config.horizontalSpacing())
            ) {
                actions.forEach { action ->
                    Box(modifier = Modifier.width(180.dp * config.paddingScale)) {
                        action()
                    }
                }
            }
        }
        else -> {
            // Büyük ekranlarda grid layout
            ResponsiveGrid(
                modifier = modifier,
                items = actions,
                minItemWidth = 200.dp,
                config = config
            )
        }
    }
}
