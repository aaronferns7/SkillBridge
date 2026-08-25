package com.example.skillbridge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skillbridge.screens.*

@Composable fun AppNavigation() {
 val nav=rememberNavController()
 NavHost(nav,"splash") {
  composable("splash"){ SplashScreen { nav.navigate("welcome"){popUpTo("splash"){inclusive=true}} } }
  composable("welcome"){ WelcomeScreen(onContinue={nav.navigate("explore"){popUpTo("welcome"){inclusive=true}}},onLogin={nav.navigate("explore")}) }
  composable("explore"){ ExploreScreen(onJob={nav.navigate("job_details/$it")},onProfile={nav.navigate("profile_view")}) }
  composable("job_details/{jobId}", listOf(navArgument("jobId"){type=NavType.LongType})){ e-> JobDetailsScreen(e.arguments?.getLong("jobId")?:1,{nav.popBackStack()},{nav.navigate("apply_project/${e.arguments?.getLong("jobId")?:1}")}) }
  composable("apply_project/{jobId}"){ ApplyProjectScreen{nav.popBackStack()} }
  composable("profile_view"){ ProfileScreen{nav.navigate("explore"){popUpTo("explore"){inclusive=false}}} }
  composable("edit_profile"){ SimpleDestination("Edit profile") { nav.popBackStack() } }
  composable("my_applications"){ SimpleDestination("My applications") { nav.popBackStack() } }
 }
}
