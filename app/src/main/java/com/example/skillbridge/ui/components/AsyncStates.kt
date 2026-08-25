package com.example.skillbridge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.skillbridge.ui.theme.BluePrimary
import com.example.skillbridge.ui.theme.TextGray

@Composable fun NoInternetState(onRetry: () -> Unit) = Column(Modifier.fillMaxSize().padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
    Icon(Icons.Default.CloudOff, null, Modifier.size(68.dp), TextGray)
    Spacer(Modifier.height(16.dp)); Text("No internet connection", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    Text("Reconnect and we’ll refresh nearby opportunities.", color = TextGray)
    Spacer(Modifier.height(20.dp)); Button(onRetry, shape = RoundedCornerShape(12.dp)) { Icon(Icons.Default.Refresh, null); Text("  Retry") }
}

@Composable fun JobCardShimmer() = Card(Modifier.fillMaxWidth().height(164.dp), shape = RoundedCornerShape(16.dp)) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(Modifier.size(46.dp).background(Color(0xFFE2E8F0), RoundedCornerShape(12.dp)))
        Box(Modifier.fillMaxWidth(.72f).height(18.dp).background(Color(0xFFE2E8F0), RoundedCornerShape(8.dp)))
        Box(Modifier.fillMaxWidth(.45f).height(14.dp).background(Color(0xFFF1F5F9), RoundedCornerShape(8.dp)))
    }
}
