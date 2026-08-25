package com.example.skillbridge.screens
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillbridge.ui.theme.*
@OptIn(ExperimentalMaterial3Api::class) @Composable fun JobDetailsScreen(id:Long,onBack:()->Unit,onApply:()->Unit){var tab by remember{mutableStateOf(0)};var save by remember{mutableStateOf(false)};val job=demoJobs.firstOrNull{it.id==id}?:demoJobs.first();Scaffold(topBar={TopAppBar(navigationIcon={IconButton(onBack){Icon(Icons.Default.ArrowBack,null)}},title={Text("Opportunity")},actions={IconButton({save=!save}){Icon(if(save)Icons.Default.Bookmark else Icons.Default.BookmarkBorder,null)}})},bottomBar={Surface(shadowElevation=10.dp){Button(onApply,Modifier.fillMaxWidth().padding(16.dp).height(54.dp),shape=RoundedCornerShape(12.dp),colors=ButtonDefaults.buttonColors(BluePrimary)){Text("Apply now",fontWeight=FontWeight.Bold)}}}){pad->Column(Modifier.padding(pad).verticalScroll(rememberScrollState()).padding(20.dp)){Row(verticalAlignment=Alignment.CenterVertically){Surface(Modifier.size(78.dp),shape=RoundedCornerShape(24.dp),color=GradientStart){Box(contentAlignment=Alignment.Center){Text(job.initials,color=Color.White,style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)}};Spacer(Modifier.width(16.dp));Column{Text(job.title,style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.ExtraBold);Row{Text(job.company,color=TextGray);Icon(Icons.Default.Verified,null,Modifier.padding(start=5.dp).size(18.dp),SuccessGreen)}}};Spacer(Modifier.height(20.dp));LazyRow(horizontalArrangement=Arrangement.spacedBy(8.dp)){items(listOf("$${job.pay}/hr","${job.distance} miles",if(job.remote)"Remote" else "On-site")){AssistChip({}, {Icon(Icons.Default.WorkOutline,null,Modifier.size(16.dp))},{Text(it)})}};Spacer(Modifier.height(22.dp));PrimaryTabRow(selectedTabIndex=tab){listOf("Description","Requirements","Milestones","Client").forEachIndexed{i,t->Tab(tab==i,{tab=i},text={Text(t)})}};Spacer(Modifier.height(16.dp));Text(when(tab){0->"Help ${job.company} bring a focused, student-built idea to life. Youâ€™ll work directly with a verified client, set clear milestones, and leave with work you can be proud to show.";1->"â€¢ Strong communication\nâ€¢ A relevant portfolio\nâ€¢ Availability this month\nâ€¢ Curiosity and reliability";2->"Week 1  Discovery & plan\nWeek 2  First delivery\nWeek 3  Review & launch";else->"Verified client\nTypically replies within a day\nEstablished 2023"},style=MaterialTheme.typography.bodyLarge,lineHeight=25.sp)}}}
