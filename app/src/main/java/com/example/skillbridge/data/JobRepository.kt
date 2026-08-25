package com.example.skillbridge.data

import com.example.skillbridge.data.local.SavedJob
import com.example.skillbridge.data.local.SavedJobDao
import com.example.skillbridge.data.remote.SkillBridgeApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobRepository @Inject constructor(private val api: SkillBridgeApiService, private val savedJobs: SavedJobDao) {
    fun getJobs(category: String? = null, maxPay: Double? = null, isRemote: Boolean? = null): Flow<UiState<List<JobDto>>> = request { api.getJobs(category, maxPay, isRemote).data.orEmpty() }
    fun getJobById(id: Long): Flow<UiState<JobDto>> = request { requireNotNull(api.getJobById(id).data) }
    fun getNearbyJobs(lat: Double, lng: Double, radius: Double): Flow<UiState<List<JobDto>>> = request { api.getNearbyJobs(lat, lng, radius).data.orEmpty() }
    fun submitApplication(application: ApplicationRequest): Flow<UiState<Unit>> = request { api.submitApplication(application); Unit }
    fun savedJobs(): Flow<List<SavedJob>> = savedJobs.getAll()
    suspend fun toggleSaved(jobId: Long, isSaved: Boolean) { if (isSaved) savedJobs.delete(jobId) else savedJobs.insert(SavedJob(jobId)) }
    private fun <T> request(block: suspend () -> T): Flow<UiState<T>> = flow {
        emit(UiState.Loading)
        try { emit(UiState.Success(block())) } catch (e: Exception) {
            val offline = e.message?.contains("Unable to resolve host", true) == true || e.message?.contains("failed to connect", true) == true
            emit(UiState.Error("We couldn’t reach SkillBridge. Check your connection and retry.", offline))
        }
    }
}
