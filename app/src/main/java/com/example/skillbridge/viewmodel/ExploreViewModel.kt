package com.example.skillbridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillbridge.data.JobDto
import com.example.skillbridge.data.JobRepository
import com.example.skillbridge.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExploreFilters(val category: String? = null, val maxPay: Double? = null, val remoteOnly: Boolean = false)
@HiltViewModel class ExploreViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {
    private val _jobs = MutableStateFlow<UiState<List<JobDto>>>(UiState.Loading); val jobs: StateFlow<UiState<List<JobDto>>> = _jobs.asStateFlow()
    private val _filters = MutableStateFlow(ExploreFilters()); val filters = _filters.asStateFlow()
    val searchQuery = MutableStateFlow("")
    init { refresh() }
    fun refresh() = viewModelScope.launch { repository.getJobs(_filters.value.category, _filters.value.maxPay, _filters.value.remoteOnly.takeIf { it }).collectLatest { _jobs.value = it } }
    fun updateFilters(filters: ExploreFilters) { _filters.value = filters; refresh() }
}
