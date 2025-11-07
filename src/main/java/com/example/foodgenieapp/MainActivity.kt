package com.example.foodgenieapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodgenieapp.ui.theme.FoodGenieAppTheme
// app/src/main/java/com/foodgenie/presentation/MainActivity.kt
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodgenie.presentation.screens.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            println("Notification permission granted")
        } else {
            println("Notification permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermissionIfNeeded()

        setContent {
            FoodGenieApp(authViewModel = authViewModel)
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}

@Composable
fun FoodGenieApp(authViewModel: AuthViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val localizationManager = LocalizationManager(context)
    val navController = rememberNavController()

    val currentUser by authViewModel.currentUser.collectAsState()

    LaunchedEffect(Unit) {
        localizationManager.updateAppLanguage(localizationManager.getCurrentLanguage())
    }

    FoodGenieTheme {
        Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = if (currentUser != null) "home" else "login"
            ) {
                composable("login") {
                    LoginScreen(
                        navController = navController,
                        viewModel = authViewModel
                    )
                }

                composable("register") {
                    RegisterScreen(
                        navController = navController,
                        viewModel = authViewModel
                    )
                }

                composable("home") {
                    HomeScreen(
                        navController = navController,
                        currentUser = currentUser,
                        authViewModel = authViewModel
                    )
                }

                composable("settings") {
                    SettingsScreen()
                }

                composable("food_menu") {
                    FoodMenuScreen(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }

                composable("cart") {
                    CartScreen(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }

                composable("orders") {
                    OrdersScreen(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}