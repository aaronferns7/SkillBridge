package com.example.skillbridge.screens
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillbridge.ui.theme.*
@Composable fun WelcomeScreen(onContinue:()->Unit,onLogin:()->Unit){var visible by remember{mutableStateOf(false)};LaunchedEffect(Unit){visible=true};Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(CardBackground).padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally){CollaborateArt();Spacer(Modifier.height(20.dp));Text("SkillBridge",style=TextStyle(brush=Brush.linearGradient(listOf(GradientStart,GradientEnd)),fontSize=42.sp,fontWeight=FontWeight.ExtraBold));Text("Your campus. Your next big opportunity.",style=MaterialTheme.typography.titleMedium);Spacer(Modifier.height(22.dp));listOf(Triple(Icons.Default.Explore,"Discover nearby projects","Work that fits around your schedule."),Triple(Icons.Default.Verified,"Connect with trusted clients","Every client is thoughtfully verified."),Triple(Icons.Default.AutoAwesome,"Build your future","Turn real projects into your portfolio.")).forEachIndexed{i,item->AnimatedVisibility(visible,enter=slideInVertically(tween(450,i*150)){it/2}+fadeIn()){Feature(item.first,item.second,item.third)};Spacer(Modifier.height(10.dp))};Spacer(Modifier.height(14.dp));Button(onClick=onContinue,Modifier.fillMaxWidth().height(54.dp),shape=RoundedCornerShape(12.dp),elevation=ButtonDefaults.buttonElevation(6.dp)){Text("Get Started",fontWeight=FontWeight.Bold)};Spacer(Modifier.height(10.dp));OutlinedButton({},Modifier.fillMaxWidth().height(54.dp),shape=RoundedCornerShape(12.dp)){Icon(Icons.Default.GMobiledata,null,tint=BluePrimary);Text("  Continue with Google")};Spacer(Modifier.height(18.dp));Row{Text("Already have an account? ",color=TextGray);Text("Login",color=BluePrimary,fontWeight=FontWeight.Bold,modifier=Modifier.clickable{onLogin()})}}}
@Composable private fun CollaborateArt(){Box(Modifier.fillMaxWidth().height(190.dp).clip(RoundedCornerShape(28.dp)).background(Brush.linearGradient(listOf(Color(0xFFEFF6FF),Color(0xFFD1FAE5))))){Box(Modifier.size(100.dp).align(Alignment.BottomStart).offset(30.dp,15.dp).background(GradientStart,CircleShape));Box(Modifier.size(76.dp).align(Alignment.TopEnd).offset((-30).dp,24.dp).background(GradientEnd,CircleShape));Icon(Icons.Default.Groups,null,Modifier.align(Alignment.Center).size(96.dp),Color.White)}}
@Composable private fun Feature(icon:androidx.compose.ui.graphics.vector.ImageVector,title:String,body:String){Card(Modifier.fillMaxWidth(),shape=RoundedCornerShape(20.dp),colors=CardDefaults.cardColors(Color.White)){Row(Modifier.padding(16.dp),verticalAlignment=Alignment.CenterVertically){Surface(color=IconBgBlue,shape=RoundedCornerShape(12.dp),modifier=Modifier.size(46.dp)){Icon(icon,null,Modifier.padding(11.dp),BluePrimary)};Spacer(Modifier.width(14.dp));Column{Text(title,fontWeight=FontWeight.Bold);Text(body,color=TextGray,style=MaterialTheme.typography.bodySmall)}}}}
