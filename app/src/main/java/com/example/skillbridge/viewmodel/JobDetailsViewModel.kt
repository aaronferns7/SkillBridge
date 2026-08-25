package com.example.skillbridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillbridge.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class JobDetailsViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {
    val job = MutableStateFlow<UiState<JobDto>>(UiState.Loading)
    val savedIds = repository.savedJobs().map { list -> list.map { it.jobId }.toSet() }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptySet())
    fun load(id: Long) = viewModelScope.launch { repository.getJobById(id).collect { job.value = it } }
    fun toggleSave(id: Long) = viewModelScope.launch { repository.toggleSaved(id, id in savedIds.value) }
}
