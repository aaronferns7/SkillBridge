package com.example.skillbridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillbridge.data.ProfileDto
import com.example.skillbridge.data.UiState
import com.example.skillbridge.data.remote.SkillBridgeApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class ProfileViewModel @Inject constructor(private val api: SkillBridgeApiService) : ViewModel() {
    val profile = MutableStateFlow<UiState<ProfileDto>>(UiState.Loading)
    fun load(id: Long = 1) = viewModelScope.launch { try { profile.value = UiState.Success(requireNotNull(api.getProfile(id).data)) } catch (_: Exception) { profile.value = UiState.Error("Profile is unavailable right now.") } }
}
