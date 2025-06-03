# 🏥 Fizik Tedavi App - Modern UI Enhanced

Modern ve kullanıcı dostu bir fizik tedavi uygulaması. **Jetpack Compose** ile geliştirilmiş, Material You tasarım sistemi kullanan ve gelişmiş animasyonlar içeren kapsamlı bir sağlık uygulaması.

## ✨ Yeni Tasarım Özellikleri

### 🎨 Modern UI Bileşenleri
- **Gradient Glass Cards**: Glassmorphism efektli modern kartlar
- **Animated Progress Cards**: İlerleme animasyonları ile akıllı istatistik kartları  
- **Floating Action Cards**: Genişleyen ve daralan aksiyon butonları
- **Gradient Buttons**: Çoklu renk geçişli modern butonlar
- **Pulse Animations**: Dikkat çekici nabız animasyonları
- **Neumorphism Cards**: Yumuşak gölge efektli tasarım
- **Segmented Controls**: Animasyonlu seçim kontrolleri

### 🌈 Gelişmiş Renk Sistemi
- **Material You Uyumlu**: Android 12+ dinamik renk desteği
- **Healthcare Palette**: Sağlık sektörüne özel renk tonları
- **Gradient Support**: Çoklu renk geçişli modern tasarım
- **Dark/Light Theme**: Otomatik tema değişimi
- **Accessibility**: Yüksek kontrast ve okunabilirlik

### 📱 Responsive Tasarım
- **Tablet Optimized**: Tablet ve büyük ekranlar için optimize
- **Dynamic Scaling**: Ekran boyutuna göre otomatik ölçekleme
- **Adaptive Layouts**: Farklı cihazlara uyum sağlayan yerleşim

## 🚀 Özellikler

### 📊 Dashboard
- **Modern Welcome Section**: Gradient arka plan ile hoş geldin alanı
- **Animated Statistics**: Canlı istatistik kartları
- **Quick Actions**: Hızlı erişim butonları
- **Daily Progress**: Günlük ilerleme takibi
- **Emergency Button**: Acil durum için terapist arama

### 💪 Egzersiz Sistemi
- **Exercise Library**: Kapsamlı egzersiz kütüphanesi
- **Progress Tracking**: Detaylı ilerleme takibi
- **Calendar View**: Takvim görünümü ile planlama
- **Video Guides**: Egzersiz videolar için hazır altyapı

### 📈 İlerleme Takibi
- **Visual Analytics**: Görsel analitik kartlar
- **Streak Tracking**: Seri takip sistemi
- **Point System**: Puan ve ödül sistemi
- **Goal Setting**: Hedef belirleme ve takip

### 👥 Sosyal Özellikler
- **Leaderboard**: Liderlik tablosu
- **Achievement System**: Başarı rozetleri için altyapı
- **Therapist Contact**: Terapist ile iletişim

## 🛠 Teknoloji Stack

- **Kotlin**: Modern Android geliştirme dili
- **Jetpack Compose**: Bildirimsel UI toolkit
- **Material 3**: En son Material Design sistemi
- **Navigation Component**: Tip-safe navigasyon
- **Coroutines**: Asenkron programlama
- **State Management**: Compose state yönetimi

## 📱 Desteklenen Android Sürümleri

- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

## 🎯 Ekran Görüntüleri

### Dashboard
- Modern gradient arka plan
- Glass effect kartlar
- Animasyonlu istatistikler
- Floating emergency button

### Navigation
- Modern bottom navigation
- Smooth transitions
- Adaptive icons

### Cards & Components
- Gradient glass cards
- Neumorphism effects
- Animated progress bars
- Modern buttons

## 🚀 Kurulum

### Gereksinimler
- Android Studio Arctic Fox veya daha yeni
- JDK 8 veya daha yeni
- Android SDK 34

### Adımlar
1. Projeyi klonlayın:
```bash
git clone https://github.com/yourusername/FizikTedaviApp.git
```

2. Android Studio'da açın

3. Gradle sync işlemini bekleyin

4. Emulator veya fiziksel cihazda çalıştırın

## 🎨 Tasarım Sistemi

### Renk Paleti
```kotlin
// Primary Colors
PrimaryBlue500 = #1976D2
HealthGreen500 = #00A651  
WellnessTeal500 = #00BFA5

// Gradient Colors
GradientStart = PrimaryBlue400
GradientMiddle = WellnessTeal400
GradientEnd = HealthGreen400
```

### Tipografi
```kotlin
// Headlines
headlineLarge: 32sp, Bold
headlineMedium: 28sp, Bold
headlineSmall: 24sp, SemiBold

// Body Text
bodyLarge: 16sp, Normal, 24sp line height
bodyMedium: 14sp, Normal, 20sp line height
```

### Spacing System
```kotlin
// Padding Values
Small: 8dp, 12dp, 16dp
Medium: 16dp, 20dp, 24dp  
Large: 24dp, 32dp, 40dp
```

## 🧩 Bileşen Kullanımı

### Gradient Glass Card
```kotlin
GradientGlassCard(
    gradientColors = listOf(
        PrimaryBlue400.copy(alpha = 0.1f),
        WellnessTeal400.copy(alpha = 0.08f),
        HealthGreen400.copy(alpha = 0.06f)
    ),
    cornerRadius = 20.dp,
    onClick = { /* action */ }
) {
    // Content
}
```

### Animated Progress Card
```kotlin
AnimatedProgressCard(
    title = "Bugünkü İlerleme",
    progress = 75f,
    icon = Icons.Default.TrendingUp,
    progressColor = HealthGreen500
)
```

### Gradient Button
```kotlin
GradientButton(
    text = "Başla",
    onClick = { /* action */ },
    gradientColors = listOf(HealthGreen400, HealthGreen600),
    leadingIcon = Icons.Default.PlayArrow
)
```

## 🔄 Animasyonlar

### Desteklenen Animasyonlar
- **Spring Animations**: Doğal hissiyat
- **Fade Transitions**: Yumuşak geçişler  
- **Scale Animations**: Büyütme/küçültme
- **Color Transitions**: Renk geçişleri
- **Progress Animations**: İlerleme animasyonları

### Animasyon Parametreleri
```kotlin
// Spring Configuration
dampingRatio = Spring.DampingRatioMediumBouncy
stiffness = Spring.StiffnessLow

// Duration Settings
shortDuration = 150ms
mediumDuration = 300ms
longDuration = 500ms
```

## 📁 Proje Yapısı

```
app/src/main/java/com/example/fiziktedaviapp/
├── components/           # Modern UI bileşenleri
│   ├── ModernSurfaces.kt    # Glass ve gradient kartlar
│   ├── ModernButtons.kt     # Animasyonlu butonlar
│   ├── CommonComponents.kt  # Genel bileşenler
│   └── ResponsiveLayout.kt  # Responsive layout yardımcıları
├── screens/             # Uygulama ekranları  
│   ├── dashboard/       # Ana sayfa
│   ├── exercises/       # Egzersiz ekranları
│   └── ...
├── navigation/          # Navigasyon yapısı
├── ui/theme/           # Tema ve stil sistemi
│   ├── Color.kt        # Modern renk paleti
│   ├── Type.kt         # Gelişmiş tipografi
│   ├── Shape.kt        # Modern şekiller
│   └── Theme.kt        # Ana tema yapılandırması
└── MainActivity.kt     # Ana aktivite
```

## 🤝 Katkıda Bulunma

1. Projeyi fork edin
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için `LICENSE` dosyasına bakın.

## 👏 Teşekkürler

- **Material Design Team**: Material You tasarım sistemi için
- **Jetpack Compose Team**: Modern UI toolkit için
- **Android Developer Community**: Sürekli geliştirme ve paylaşım için

## 📞 İletişim

- **Geliştirici**: [Your Name]
- **Email**: your.email@example.com
- **LinkedIn**: [Your LinkedIn Profile]
- **GitHub**: [Your GitHub Profile]

---

**Fizik Tedavi App** - Modern tasarım ile sağlık takibi artık daha kolay! 🚀 