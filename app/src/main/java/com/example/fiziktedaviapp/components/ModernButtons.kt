package com.example.fiziktedaviapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiziktedaviapp.ui.theme.*
import com.example.fiziktedaviapp.ui.theme.GradientStart
import com.example.fiziktedaviapp.ui.theme.GradientMiddle
import com.example.fiziktedaviapp.ui.theme.GradientEnd
import com.example.fiziktedaviapp.ui.theme.GlassWhite
import com.example.fiziktedaviapp.ui.theme.HealthGreen500
import com.example.fiziktedaviapp.ui.theme.PrimaryBlue500
import com.example.fiziktedaviapp.ui.theme.SurfaceGray100
import com.example.fiziktedaviapp.ui.theme.SurfaceGray200
import com.example.fiziktedaviapp.ui.theme.TextPrimary900

/**
 * Material You tasarım diline uygun modern ana buton
 */
@Composable
fun ModernPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: ImageVector? = null,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Basıldığında buton etkisi için animasyon
    val scale = if (isPressed) 0.98f else 1f
    
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .height(56.dp),
        enabled = enabled && !isLoading,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        interactionSource = interactionSource
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp
            )
        } else {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/**
 * Material 3 tasarım diline uygun yükseltilmiş buton
 */
@Composable
fun ModernElevatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    shape: Shape = MaterialTheme.shapes.small
) {
    // Basıldığında yükseklik değişimi
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 0.dp else 2.dp,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        ),
        label = "Button Elevation"
    )
    
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        shape = shape,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = elevation,
            pressedElevation = 0.dp,
            focusedElevation = 2.dp
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        interactionSource = interactionSource
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * Material 3 tasarım diline uygun tonlu buton
 */
@Composable
fun ModernTonalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * Modern Action Button - Ana işlemleri çağırmak için önemli buton
 */
@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isAccent: Boolean = false,
    isFullWidth: Boolean = true,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    // Animasyonlu container rengi
    val containerColor by animateColorAsState(
        targetValue = if (isAccent)
            MaterialTheme.colorScheme.tertiary
        else
            MaterialTheme.colorScheme.primary,
        label = "Container Color"
    )
    
    val contentColor = if (isAccent)
        MaterialTheme.colorScheme.onTertiary
    else
        MaterialTheme.colorScheme.onPrimary
    
    val buttonModifier = if (isFullWidth) {
        modifier.fillMaxWidth()
    } else {
        modifier
    }
    
    Button(
        onClick = onClick,
        modifier = buttonModifier
            .height(56.dp),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        if (isLoading) {
            // Yükleniyor animasyonu
            val infiniteTransition = rememberInfiniteTransition(label = "Infinite Animation")
            val angle by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Modern Pulsating Button - Dikkat çekmek için animasyonlu buton
 */
@Composable
fun PulsatingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPulsating: Boolean = true,
    color: Color = MaterialTheme.colorScheme.tertiary
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // Pulsating animasyonlu arka plan efekti
        if (isPulsating) {
            val infiniteTransition = rememberInfiniteTransition(label = "Pulse Animation")
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 1.1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.6f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            
            // Pulsating background
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .scale(scale)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color.copy(alpha = alpha))
            )
        }
        
        // Butonun kendisi
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/**
 * Gradient Button with Animation Effects
 */
@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: ImageVector? = null,
    gradientColors: List<Color> = listOf(GradientStart, GradientMiddle, GradientEnd),
    contentColor: Color = Color.White,
    cornerRadius: Dp = 16.dp,
    elevation: Dp = 8.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "button_scale"
    )
    
    val shadowElevation by animateDpAsState(
        targetValue = if (isPressed) elevation / 2 else elevation,
        animationSpec = tween(150),
        label = "shadow_elevation"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        Button(
            onClick = onClick,
            enabled = enabled && !isLoading,
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(
                    elevation = shadowElevation,
                    shape = RoundedCornerShape(cornerRadius),
                    ambientColor = gradientColors.first().copy(alpha = 0.3f),
                    spotColor = gradientColors.last().copy(alpha = 0.3f)
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = if (enabled) gradientColors else gradientColors.map { it.copy(alpha = 0.5f) },
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    ),
                    shape = RoundedCornerShape(cornerRadius)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = contentColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = contentColor.copy(alpha = 0.6f)
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(cornerRadius)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = contentColor,
                        strokeWidth = 2.dp
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        leadingIcon?.let { icon ->
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = contentColor
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = text,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = contentColor
                        )
                    }
                }
            }
        }
    }
}

/**
 * Glassmorphism Button
 */
@Composable
fun GlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    backgroundColor: Color = GlassWhite,
    contentColor: Color = TextPrimary900,
    borderColor: Color = Color.White.copy(alpha = 0.3f),
    cornerRadius: Dp = 16.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "glass_button_scale"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(color = contentColor.copy(alpha = 0.2f)),
                enabled = enabled,
                onClick = onClick
            )
            .drawBehind {
                // Glass border effect
                drawRoundRect(
                    color = borderColor,
                    size = Size(size.width, size.height),
                    cornerRadius = CornerRadius(cornerRadius.toPx()),
                    style = Stroke(width = 1.dp.toPx())
                )
            },
        shape = RoundedCornerShape(cornerRadius),
        color = backgroundColor,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                leadingIcon?.let { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
            }
        }
    }
}

/**
 * Floating Action Button with Pulse Animation
 */
@Composable
fun PulseFloatingActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryBlue500,
    contentColor: Color = Color.White,
    size: Dp = 56.dp,
    isPulsing: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_transition")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isPulsing) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = if (isPulsing) 0.3f else 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Pulse ring
        if (isPulsing) {
            Canvas(
                modifier = Modifier.size(size * 1.5f)
            ) {
                drawCircle(
                    color = backgroundColor.copy(alpha = alpha),
                    radius = (size.toPx() / 2) * scale
                )
            }
        }
        
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(size),
            containerColor = backgroundColor,
            contentColor = contentColor,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Segmented Button Group
 */
@Composable
fun SegmentedButtonGroup(
    options: List<String>,
    selectedIndex: Int,
    onSelectionChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SurfaceGray100,
    selectedColor: Color = PrimaryBlue500,
    contentColor: Color = TextPrimary900,
    selectedContentColor: Color = Color.White
) {
    val animatedSelectedIndex by animateIntAsState(
        targetValue = selectedIndex,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "selected_index"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
    ) {
        options.forEachIndexed { index, option ->
            val isSelected = index == selectedIndex
            val animatedBackgroundColor by animateColorAsState(
                targetValue = if (isSelected) selectedColor else Color.Transparent,
                animationSpec = tween(300),
                label = "background_color_$index"
            )
            
            val animatedContentColor by animateColorAsState(
                targetValue = if (isSelected) selectedContentColor else contentColor,
                animationSpec = tween(300),
                label = "content_color_$index"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(animatedBackgroundColor)
                    .clickable { onSelectionChanged(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                    color = animatedContentColor
                )
            }
        }
    }
}

/**
 * Icon Button with Background Animation
 */
@Composable
fun AnimatedIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = SurfaceGray100,
    contentColor: Color = TextPrimary900,
    size: Dp = 48.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "icon_button_scale"
    )
    
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.8f else 1f,
        animationSpec = tween(150),
        label = "background_alpha"
    )

    Surface(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = CircleShape,
        color = backgroundColor.copy(alpha = backgroundAlpha),
        shadowElevation = if (isPressed) 2.dp else 4.dp,
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Toggle Button with Smooth Animation
 */
@Composable
fun AnimatedToggleButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
    checkedColor: Color = HealthGreen500,
    uncheckedColor: Color = SurfaceGray200,
    contentColor: Color = Color.White,
    size: Dp = 56.dp
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isChecked) checkedColor else uncheckedColor,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "toggle_color"
    )
    
    val rotation by animateFloatAsState(
        targetValue = if (isChecked) 180f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "icon_rotation"
    )

    Surface(
        modifier = modifier.size(size),
        shape = RoundedCornerShape(16.dp),
        color = animatedColor,
        shadowElevation = 6.dp,
        onClick = { onCheckedChange(!isChecked) }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isChecked) checkedIcon else uncheckedIcon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier
                    .size(28.dp)
                    .graphicsLayer { rotationZ = rotation }
            )
        }
    }
}

/**
 * Chip Button with Selection Animation
 */
@Composable
fun AnimatedChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    selectedColor: Color = PrimaryBlue500,
    unselectedColor: Color = SurfaceGray100,
    selectedContentColor: Color = Color.White,
    unselectedContentColor: Color = TextPrimary900
) {
    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "chip_background"
    )
    
    val animatedContentColor by animateColorAsState(
        targetValue = if (isSelected) selectedContentColor else unselectedContentColor,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "chip_content"
    )
    
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 6.dp else 2.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "chip_elevation"
    )

    Surface(
        modifier = modifier
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        color = animatedBackgroundColor,
        shadowElevation = animatedElevation,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            leadingIcon?.let { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = animatedContentColor
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                color = animatedContentColor
            )
        }
    }
}
