package com.example.skillbridge.screens
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.skillbridge.ui.theme.*
@Composable fun ApplyProjectScreen(onBack:()->Unit){var step by remember{mutableIntStateOf(0)};var intro by remember{mutableStateOf("")};var portfolio by remember{mutableStateOf(0)};var done by remember{mutableStateOf(false)};if(done)AlertDialog(onDismissRequest={onBack()},icon={Icon(Icons.Default.Celebration,null,SuccessGreen)},title={Text("Application sent!")},text={Text("Nice work — the client will see your application shortly.")},confirmButton={Button(onBack){Text("Back to opportunities")}});Scaffold(topBar={TopAppBar(title={Text("Apply to project")},navigationIcon={IconButton(onBack){Icon(Icons.Default.Close,null)}})}){pad->Column(Modifier.padding(pad).padding(20.dp)){LinearProgressIndicator((step+1)/3f,Modifier.fillMaxWidth(),color=GradientEnd);Text("Step ${step+1} of 3",Modifier.padding(top=12.dp),color=TextGray);Spacer(Modifier.height(14.dp));AnimatedContent(step,label="step"){s->when(s){0->Column{Text("Introduce yourself",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold);Text("Tell the client why you’re a great fit.",color=TextGray);OutlinedTextField(intro,{intro=it},Modifier.fillMaxWidth().padding(top=18.dp).height(180.dp),placeholder={Text("Write a thoughtful introduction…")},supportingText={Text("${intro.length}/500")},isError=intro.isNotEmpty()&&intro.length<20)};1->Column{Text("Choose your portfolio",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold);Spacer(Modifier.height(16.dp));Row(horizontalArrangement=Arrangement.spacedBy(10.dp)){listOf("Campus app","Brand refresh","Data story").forEachIndexed{i,n->FilterChip(portfolio==i,{portfolio=i},{Text(n)},Modifier.height(64.dp))}}};else->Column{Text("Timeline & compensation",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold);OutlinedTextField("2 weeks",{},Modifier.fillMaxWidth().padding(top=16.dp),label={Text("Proposed timeline")});OutlinedTextField("$40/hr",{},Modifier.fillMaxWidth().padding(top=12.dp),label={Text("Proposed compensation")});OutlinedButton({},Modifier.padding(top=16.dp)){Icon(Icons.Default.AttachFile,null);Text(" Add attachment")}}}};Spacer(Modifier.weight(1f));Button({if(step<2)step++ else done=true},Modifier.fillMaxWidth().height(54.dp),enabled=step!=0||intro.length>=20,shape=RoundedCornerShape(12.dp)){Text(if(step==2)"Submit application" else "Continue")}}}}
