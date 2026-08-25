package com.example.skillbridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillbridge.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApplicationForm(val step: Int = 0, val intro: String = "", val portfolio: Int = 0, val timeline: String = "", val compensation: String = "")
@HiltViewModel class ApplyViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {
    val form = MutableStateFlow(ApplicationForm()); val submission = MutableStateFlow<UiState<Unit>?>(null)
    fun update(transform: (ApplicationForm) -> ApplicationForm) { form.value = transform(form.value) }
    fun valid() = when (form.value.step) { 0 -> form.value.intro.length >= 20; 2 -> form.value.timeline.isNotBlank() && form.value.compensation.isNotBlank(); else -> true }
    fun submit(jobId: Long) = viewModelScope.launch { repository.submitApplication(ApplicationRequest(jobId, 1, form.value.intro, form.value.compensation)).collect { submission.value = it } }
}
