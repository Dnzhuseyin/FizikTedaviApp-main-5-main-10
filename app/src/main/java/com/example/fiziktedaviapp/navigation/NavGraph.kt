package com.example.fiziktedaviapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fiziktedaviapp.screens.auth.LoginScreen
import com.example.fiziktedaviapp.screens.auth.RegisterScreen
import com.example.fiziktedaviapp.screens.auth.ForgotPasswordScreen
import com.example.fiziktedaviapp.screens.auth.OnboardingScreen
import com.example.fiziktedaviapp.screens.dashboard.DashboardScreen
import com.example.fiziktedaviapp.screens.exercises.ExerciseDetailScreen
import com.example.fiziktedaviapp.screens.exercises.ExerciseListScreen
import com.example.fiziktedaviapp.screens.exercises.ExerciseCalendarScreen
import com.example.fiziktedaviapp.screens.leaderboard.LeaderboardScreen
import com.example.fiziktedaviapp.screens.notifications.NotificationsScreen
import com.example.fiziktedaviapp.screens.profile.ProfileScreen
import com.example.fiziktedaviapp.screens.therapist.TherapistCallScreen
import com.example.fiziktedaviapp.screens.settings.SettingsScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object Onboarding : Screen("onboarding")
    object Dashboard : Screen("dashboard")
    object ExerciseList : Screen("exercise_list")
    object ExerciseDetail : Screen("exercise_detail/{exerciseId}") {
        fun createRoute(exerciseId: String) = "exercise_detail/$exerciseId"
    }
    object ExerciseCalendar : Screen("exercise_calendar")
    object Leaderboard : Screen("leaderboard")
    object Profile : Screen("profile")
    object TherapistCall : Screen("therapist_call")
    object Settings : Screen("settings")
    object Notifications : Screen("notifications")
}

@Composable
fun NavGraph(
    navController: NavHostController, 
    startDestination: String = Screen.Login.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController)
        }
        
        composable(Screen.ExerciseList.route) {
            ExerciseListScreen(navController)
        }
        
        composable(
            route = Screen.ExerciseDetail.route,
            arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: "0"
            ExerciseDetailScreen(navController, exerciseId)
        }
        
        composable(Screen.ExerciseCalendar.route) {
            ExerciseCalendarScreen(navController)
        }
        
        composable(Screen.Leaderboard.route) {
            LeaderboardScreen(navController)
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        
        composable(Screen.TherapistCall.route) {
            TherapistCallScreen(navController)
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController)
        }
    }
} 