package com.example.fiziktedaviapp.ui.theme

import androidx.compose.ui.graphics.Color

// MODERN GRADIENT HEALTH THEME - 2024 Design System
// ==================================================

// Primary Color Palette - Enhanced Blues
val PrimaryBlue50 = Color(0xFFE8F1FF)
val PrimaryBlue100 = Color(0xFFD1E4FF)
val PrimaryBlue200 = Color(0xFFA3C9FF)
val PrimaryBlue300 = Color(0xFF75ADFF)
val PrimaryBlue400 = Color(0xFF4792FF)
val PrimaryBlue500 = Color(0xFF1976D2)  // Main Primary
val PrimaryBlue600 = Color(0xFF1565C0)
val PrimaryBlue700 = Color(0xFF0D47A1)
val PrimaryBlue800 = Color(0xFF08306B)
val PrimaryBlue900 = Color(0xFF041C3D)

// Health Green Palette - Medical & Wellness
val HealthGreen50 = Color(0xFFE8F7ED)
val HealthGreen100 = Color(0xFFD1EFDB)
val HealthGreen200 = Color(0xFFA3DFB7)
val HealthGreen300 = Color(0xFF75CF93)
val HealthGreen400 = Color(0xFF47BF6F)
val HealthGreen500 = Color(0xFF00A651)  // Main Secondary
val HealthGreen600 = Color(0xFF008A44)
val HealthGreen700 = Color(0xFF006D37)
val HealthGreen800 = Color(0xFF00512A)
val HealthGreen900 = Color(0xFF00341C)

// Wellness Teal - Calming & Professional
val WellnessTeal50 = Color(0xFFE6FFFC)
val WellnessTeal100 = Color(0xFFCCFFF9)
val WellnessTeal200 = Color(0xFF99FFF3)
val WellnessTeal300 = Color(0xFF66FFED)
val WellnessTeal400 = Color(0xFF33FFE7)
val WellnessTeal500 = Color(0xFF00BFA5)  // Main Tertiary
val WellnessTeal600 = Color(0xFF00A693)
val WellnessTeal700 = Color(0xFF008C80)
val WellnessTeal800 = Color(0xFF00736E)
val WellnessTeal900 = Color(0xFF00595B)

// Gradient Colors for Modern Effects
val GradientStart = PrimaryBlue400
val GradientMiddle = WellnessTeal400
val GradientEnd = HealthGreen400

// Status & Utility Colors
val SuccessGreen = Color(0xFF00C853)
val WarningOrange = Color(0xFFFF9800)
val ErrorRed = Color(0xFFE53E3E)
val InfoBlue = Color(0xFF2196F3)

// Surface & Background Enhanced
val SurfaceWhite = Color(0xFFFFFEFE)
val SurfaceCream = Color(0xFFFAF9F9)
val SurfaceGray50 = Color(0xFFF8FAFC)
val SurfaceGray100 = Color(0xFFF1F5F9)
val SurfaceGray200 = Color(0xFFE2E8F0)

// Text Colors - Better Readability
val TextPrimary900 = Color(0xFF0F172A)
val TextSecondary700 = Color(0xFF334155)
val TextTertiary500 = Color(0xFF64748B)
val TextDisabled400 = Color(0xFF94A3B8)

// Glass Effect Colors
val GlassWhite = Color(0x80FFFFFF)
val GlassDark = Color(0x40000000)

// Material You Light Theme - Enhanced
val md_theme_light_primary = PrimaryBlue500
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = PrimaryBlue100
val md_theme_light_onPrimaryContainer = PrimaryBlue900

val md_theme_light_secondary = HealthGreen500
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = HealthGreen100
val md_theme_light_onSecondaryContainer = HealthGreen900

val md_theme_light_tertiary = WellnessTeal500
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = WellnessTeal100
val md_theme_light_onTertiaryContainer = WellnessTeal900

val md_theme_light_error = ErrorRed
val md_theme_light_errorContainer = Color(0xFFFFE6E6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF5F2120)

val md_theme_light_background = SurfaceCream
val md_theme_light_onBackground = TextPrimary900
val md_theme_light_surface = SurfaceWhite
val md_theme_light_onSurface = TextPrimary900
val md_theme_light_surfaceVariant = SurfaceGray100
val md_theme_light_onSurfaceVariant = TextSecondary700
val md_theme_light_outline = TextTertiary500
val md_theme_light_inverseOnSurface = SurfaceWhite
val md_theme_light_inverseSurface = TextPrimary900
val md_theme_light_inversePrimary = PrimaryBlue200

// Material You Dark Theme - Enhanced
val md_theme_dark_primary = PrimaryBlue300
val md_theme_dark_onPrimary = PrimaryBlue900
val md_theme_dark_primaryContainer = PrimaryBlue700
val md_theme_dark_onPrimaryContainer = PrimaryBlue100

val md_theme_dark_secondary = HealthGreen300
val md_theme_dark_onSecondary = HealthGreen900
val md_theme_dark_secondaryContainer = HealthGreen700
val md_theme_dark_onSecondaryContainer = HealthGreen100

val md_theme_dark_tertiary = WellnessTeal300
val md_theme_dark_onTertiary = WellnessTeal900
val md_theme_dark_tertiaryContainer = WellnessTeal700
val md_theme_dark_onTertiaryContainer = WellnessTeal100

val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)

val md_theme_dark_background = Color(0xFF0F1419)
val md_theme_dark_onBackground = Color(0xFFE4E6EA)
val md_theme_dark_surface = Color(0xFF1A1D23)
val md_theme_dark_onSurface = Color(0xFFE4E6EA)
val md_theme_dark_surfaceVariant = Color(0xFF42474E)
val md_theme_dark_onSurfaceVariant = Color(0xFFC2C7CE)
val md_theme_dark_outline = Color(0xFF8C9199)
val md_theme_dark_inverseOnSurface = Color(0xFF2D3135)
val md_theme_dark_inverseSurface = Color(0xFFE4E6EA)
val md_theme_dark_inversePrimary = PrimaryBlue500

// Legacy Color Mappings for Compatibility
val Primary = md_theme_light_primary
val PrimaryLight = md_theme_light_primaryContainer
val PrimaryDark = md_theme_dark_primary

val Secondary = md_theme_light_secondary
val SecondaryLight = md_theme_light_secondaryContainer
val SecondaryDark = md_theme_dark_secondary

val Surface = md_theme_light_surface
val SurfaceLight = md_theme_light_background
val SurfaceDark = md_theme_dark_surface
val Background = md_theme_light_background
val BackgroundLight = md_theme_light_surface
val BackgroundDark = md_theme_dark_background

val Error = md_theme_light_error

val TextPrimary = md_theme_light_onSurface
val TextSecondary = md_theme_light_onSurfaceVariant
val TextHint = md_theme_light_outline

val DarkBlue = md_theme_light_primary
val LightBlue = md_theme_light_primaryContainer
val MediumBlue = md_theme_light_tertiary

val DarkGreen = md_theme_light_secondary
val MediumGreen = HealthGreen400
val LightGreen = md_theme_light_secondaryContainer

val WarningYellow = WarningOrange
val ErrorRed = md_theme_light_error