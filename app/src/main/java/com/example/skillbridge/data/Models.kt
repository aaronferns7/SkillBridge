package com.example.skillbridge.data

data class ApiEnvelope<T>(val success: Boolean, val data: T?, val message: String)
data class JobDto(val id: Long, val title: String, val company: String, val payRate: Double, val distance: Double, val location: String, val remote: Boolean, val tags: String, val verified: Boolean, val postedAt: String? = null)
data class ApplicationRequest(val jobId: Long, val studentId: Long, val introText: String, val proposedRate: String)
data class ProfileDto(val id: Long, val name: String, val email: String, val university: String, val bio: String?, val profilePicUrl: String?, val rating: Double, val completedProjects: Int, val verified: Boolean, val role: String)

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String, val noInternet: Boolean = false) : UiState<Nothing>
}
