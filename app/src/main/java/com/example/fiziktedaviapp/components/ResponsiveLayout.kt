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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

// Ekran boyutu kategorileri
enum class ScreenSizeClass {
    COMPACT,    // Küçük telefonlar (0-600dp)
    MEDIUM,     // Büyük telefonlar ve küçük tabletler (600-840dp)
    EXPANDED    // Tabletler ve daha büyük ekranlar (840dp+)
}

// Lokal kompozisyon için güncel ekran boyutu bilgisini sağlar
val LocalScreenSizeClass = compositionLocalOf { ScreenSizeClass.COMPACT }

/**
 * Ekran boyutuna göre ScreenSizeClass değerini belirler
 */
@Composable
fun determineScreenSizeClass(): ScreenSizeClass {
    val configuration = LocalConfiguration.current
    val widthDp = configuration.screenWidthDp.dp
    
    return when {
        widthDp < 600.dp -> ScreenSizeClass.COMPACT
        widthDp < 840.dp -> ScreenSizeClass.MEDIUM
        else -> ScreenSizeClass.EXPANDED
    }
}

/**
 * Responsive bir sayfa düzeni sağlayan bileşen.
 * Ekran boyutuna göre otomatik olarak düzeni ayarlar.
 */
@Composable
fun ResponsivePage(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    scrollable: Boolean = true,
    applySystemInsets: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val screenSizeClass = determineScreenSizeClass()
    
    // Ekran boyutuna göre padding değerlerini ayarla
    val horizontalPadding = when (screenSizeClass) {
        ScreenSizeClass.COMPACT -> 16.dp
        ScreenSizeClass.MEDIUM -> 24.dp
        ScreenSizeClass.EXPANDED -> 32.dp
    }
    
    // Sistem insetlerini hesapla
    val statusBarPadding = if (applySystemInsets) {
        WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    } else {
        0.dp
    }
    
    val navigationBarPadding = if (applySystemInsets) {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    } else {
        0.dp
    }
    
    // ContentPadding'i ekran boyutuna göre ayarla
    val topPadding = contentPadding.calculateTopPadding() + statusBarPadding
    val bottomPadding = contentPadding.calculateBottomPadding() + navigationBarPadding
    
    val finalPadding = PaddingValues(
        start = horizontalPadding,
        top = topPadding,
        end = horizontalPadding,
        bottom = bottomPadding
    )
    
    CompositionLocalProvider(LocalScreenSizeClass provides screenSizeClass) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            if (scrollable) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(finalPadding)
                        .verticalScroll(scrollState),
                    horizontalAlignment = horizontalAlignment,
                    verticalArrangement = verticalArrangement
                ) {
                    content()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(finalPadding),
                    horizontalAlignment = horizontalAlignment,
                    verticalArrangement = verticalArrangement
                ) {
                    content()
                }
            }
        }
    }
}

/**
 * Ekran boyutuna göre adaptif görünüm sunan bileşen
 * Küçük ekranlarda dikey, büyük ekranlarda yatay görünüm sağlar
 */
@Composable
fun AdaptiveLayout(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalSpacing: Dp = 16.dp,
    horizontalSpacing: Dp = 24.dp,
    firstContent: @Composable BoxScope.() -> Unit,
    secondContent: @Composable BoxScope.() -> Unit
) {
    val screenSizeClass = LocalScreenSizeClass.current
    
    when (screenSizeClass) {
        // Küçük ekranlarda dikey düzen
        ScreenSizeClass.COMPACT -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    firstContent()
                }
                
                Spacer(modifier = Modifier.height(verticalSpacing))
                
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    secondContent()
                }
            }
        }
        
        // Orta ve büyük ekranlarda yatay düzen
        else -> {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = verticalAlignment,
                horizontalArrangement = horizontalArrangement
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    firstContent()
                }
                
                Spacer(modifier = Modifier.width(horizontalSpacing))
                
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    secondContent()
                }
            }
        }
    }
}

/**
 * Ekran boyutuna göre grid düzeni sağlayan bileşen
 */
@Composable
fun ResponsiveGrid(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>
) {
    val screenSizeClass = LocalScreenSizeClass.current
    
    // Ekran boyutuna göre grid sütun sayısını belirle
    val columns = when (screenSizeClass) {
        ScreenSizeClass.COMPACT -> 1
        ScreenSizeClass.MEDIUM -> 2
        ScreenSizeClass.EXPANDED -> 3
    }
    
    // Grid düzeni için chunk'lara ayır
    val chunkedItems = items.chunked(columns)
    
    Column(modifier = modifier) {
        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        item()
                    }
                }
                
                // Eksik hücreleri doldurmak için boş spacer'lar ekle
                repeat(columns - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * Ekran boyutuna göre maksimum genişlik kısıtlaması sağlayan bileşen
 */
@Composable
fun MaxWidthContainer(
    modifier: Modifier = Modifier,
    maxWidth: Dp = 840.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (LocalScreenSizeClass.current == ScreenSizeClass.EXPANDED) {
                        Modifier.width(maxWidth)
                    } else {
                        Modifier
                    }
                ),
        ) {
            content()
        }
    }
}
