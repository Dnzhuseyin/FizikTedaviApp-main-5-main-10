package com.example.fiziktedaviapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.fiziktedaviapp.ui.theme.*

/**
 * Modern tonal kart bileşeni - Material 3'ün "Container" tasarım dilini kullanan modern kart
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TonalCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    tonalElevation: Dp = 4.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Basıldığında veya basılı tutulduğunda animasyonlu elevation değişimi
    val animatedElevation by animateDpAsState(
        targetValue = if (isPressed) (tonalElevation - 2.dp).coerceAtLeast(0.dp) else tonalElevation,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "Elevation Animation"
    )
    
    // Basıldığında hafif renk değişimi için animasyon
    val animatedContainerColor by animateColorAsState(
        targetValue = if (isPressed) 
            MaterialTheme.colorScheme.surfaceVariant 
        else 
            MaterialTheme.colorScheme.surface,
        label = "Container Color Animation"
    )
    
    Card(
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = animatedElevation,
                shape = shape,
                clip = false
            )
            .zIndex(if (isPressed) 1f else 0f),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = animatedContainerColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}

/**
 * Modern içerik kartı - Material 3'ün renk tonlarını kullanan içerik kartı
 */
@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    tonalElevation: Dp = 2.dp,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = tonalElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}

/**
 * Vurgulu modern kart - Önemli bilgileri vurgulamak için kullanılır
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccentCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.outlinedCardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                content()
            }
        }
    } else {
        OutlinedCard(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.outlinedCardColors(
                containerColor = containerColor,
                contentColor = contentColor
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                content()
            }
        }
    }
}

/**
 * Modern info kartı - Bilgi vermek amacıyla kullanılan kart
 */
@Composable
fun InfoCard(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f),
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor.copy(alpha = 0.8f)
            )
        }
    }
}

/**
 * Modern Gradient Card with Glass Effect
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradientGlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    gradientColors: List<Color> = listOf(
        GradientStart.copy(alpha = 0.1f),
        GradientMiddle.copy(alpha = 0.08f),
        GradientEnd.copy(alpha = 0.06f)
    ),
    borderGradient: List<Color> = listOf(
        GradientStart.copy(alpha = 0.3f),
        GradientMiddle.copy(alpha = 0.2f),
        GradientEnd.copy(alpha = 0.1f)
    ),
    blurRadius: Dp = 0.dp,
    cornerRadius: Dp = 20.dp,
    elevation: Dp = 8.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "card_scale"
    )
    
    val shadowElevation by animateDpAsState(
        targetValue = if (isPressed) elevation / 2 else elevation,
        animationSpec = tween(200),
        label = "shadow_elevation"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        // Background card with gradient and glass effect
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = shadowElevation,
                    shape = RoundedCornerShape(cornerRadius),
                    ambientColor = GradientStart.copy(alpha = 0.1f),
                    spotColor = GradientEnd.copy(alpha = 0.15f)
                )
                .then(
                    if (onClick != null) {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                isPressed = true
                                onClick()
                            }
                        )
                    } else Modifier
                )
                .drawBehind {
                    // Draw gradient background
                    val gradient = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height)
                    )
                    drawRect(gradient)
                    
                    // Draw gradient border
                    val borderGradientBrush = Brush.linearGradient(
                        colors = borderGradient,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height)
                    )
                    drawRoundRect(
                        brush = borderGradientBrush,
                        size = Size(size.width, size.height),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius.toPx()),
                        style = Stroke(width = 2.dp.toPx())
                    )
                },
            shape = RoundedCornerShape(cornerRadius),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .then(
                        if (blurRadius > 0.dp) Modifier.blur(blurRadius) else Modifier
                    ),
                content = content
            )
        }
        
        // Glass overlay effect
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius))
        ) {
            val shimmerGradient = Brush.linearGradient(
                colors = listOf(
                    GlassWhite.copy(alpha = 0.4f),
                    Color.Transparent,
                    GlassWhite.copy(alpha = 0.2f)
                ),
                start = Offset(0f, 0f),
                end = Offset(size.width / 2, 0f)
            )
            drawRect(shimmerGradient)
        }
    }
    
    LaunchedEffect(isPressed) {
        if (isPressed) {
            kotlinx.coroutines.delay(100)
            isPressed = false
        }
    }
}

/**
 * Floating Action Card with Morphing Animation
 */
@Composable
fun FloatingActionCard(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryBlue500,
    contentColor: Color = Color.White,
    isExpanded: Boolean = false
) {
    var expanded by remember { mutableStateOf(isExpanded) }
    val animatedWidth by animateDpAsState(
        targetValue = if (expanded) 280.dp else 56.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "width_animation"
    )
    
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(300),
        label = "icon_rotation"
    )

    Surface(
        modifier = modifier
            .width(animatedWidth)
            .height(56.dp)
            .clickable { 
                expanded = !expanded
                if (expanded) onClick()
            },
        shape = RoundedCornerShape(28.dp),
        color = backgroundColor,
        shadowElevation = 12.dp,
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer { rotationZ = rotation }
            )
            
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        color = contentColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    subtitle?.let {
                        Text(
                            text = it,
                            color = contentColor.copy(alpha = 0.8f),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

/**
 * Neumorphism Card Design
 */
@Composable
fun NeumorphismCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    isPressed: Boolean = false,
    backgroundColor: Color = SurfaceGray50,
    cornerRadius: Dp = 16.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 8.dp,
        animationSpec = tween(200),
        label = "neumorphism_elevation"
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .drawBehind {
                // Inner shadow effect
                val shadowColor = Color.Black.copy(alpha = 0.05f)
                val lightColor = Color.White.copy(alpha = 0.8f)
                
                // Top-left light shadow
                drawRoundRect(
                    color = lightColor,
                    topLeft = Offset(-2.dp.toPx(), -2.dp.toPx()),
                    size = Size(size.width + 4.dp.toPx(), size.height + 4.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius.toPx()),
                    style = Stroke(width = 1.dp.toPx())
                )
                
                // Bottom-right dark shadow
                drawRoundRect(
                    color = shadowColor,
                    topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                    size = Size(size.width - 4.dp.toPx(), size.height - 4.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius((cornerRadius - 2.dp).toPx()),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            )
            .padding(16.dp),
        content = content
    )
}

/**
 * Animated Progress Card
 */
@Composable
fun AnimatedProgressCard(
    title: String,
    progress: Float,
    maxProgress: Float = 100f,
    unit: String = "%",
    icon: ImageVector? = null,
    progressColor: Color = HealthGreen500,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier
) {
    var animatedProgress by remember { mutableStateOf(0f) }
    
    LaunchedEffect(progress) {
        animatedProgress = progress
    }
    
    val animatedValue by animateFloatAsState(
        targetValue = animatedProgress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "progress_animation"
    )

    GradientGlassCard(
        modifier = modifier,
        gradientColors = listOf(
            progressColor.copy(alpha = 0.1f),
            progressColor.copy(alpha = 0.05f),
            Color.Transparent
        ),
        cornerRadius = 20.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = progressColor.copy(alpha = 0.1f)
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = title,
                        tint = progressColor,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Custom progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(SurfaceGray200)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(animatedValue / maxProgress)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        progressColor,
                                        progressColor.copy(alpha = 0.8f)
                                    )
                                ),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${animatedValue.toInt()}$unit",
                    style = MaterialTheme.typography.bodyMedium,
                    color = progressColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
