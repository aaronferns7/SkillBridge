package com.example.skillbridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.skillbridge.navigation.AppNavigation
import com.example.skillbridge.ui.theme.SkillBridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); WindowCompat.setDecorFitsSystemWindows(window,false); setContent { SkillBridgeTheme { AppNavigation() } } }
}
