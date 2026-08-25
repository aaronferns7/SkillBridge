package com.example.skillbridge.data.remote

import com.example.skillbridge.data.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SkillBridgeApiService {
    @GET("api/jobs") suspend fun getJobs(@Query("category") category: String? = null, @Query("maxPay") maxPay: Double? = null, @Query("isRemote") isRemote: Boolean? = null): ApiEnvelope<List<JobDto>>
    @GET("api/jobs/{id}") suspend fun getJobById(@Path("id") id: Long): ApiEnvelope<JobDto>
    @GET("api/jobs/nearby") suspend fun getNearbyJobs(@Query("lat") lat: Double, @Query("lng") lng: Double, @Query("radius") radius: Double): ApiEnvelope<List<JobDto>>
    @POST("api/applications") suspend fun submitApplication(@Body application: ApplicationRequest): ApiEnvelope<Any>
    @GET("api/users/{id}") suspend fun getProfile(@Path("id") id: Long): ApiEnvelope<ProfileDto>
}
