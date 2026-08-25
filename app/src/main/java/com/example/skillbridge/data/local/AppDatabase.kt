package com.example.skillbridge.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "saved_jobs") data class SavedJob(@PrimaryKey val jobId: Long, val savedAt: Long = System.currentTimeMillis())
@Entity(tableName = "cached_jobs") data class CachedJob(@PrimaryKey val id: Long, val title: String, val company: String, val cachedAt: Long = System.currentTimeMillis())
@Entity(tableName = "draft_applications") data class DraftApplication(@PrimaryKey val jobId: Long, val introduction: String, val updatedAt: Long = System.currentTimeMillis())

@Dao interface SavedJobDao {
    @Query("SELECT * FROM saved_jobs ORDER BY savedAt DESC") fun getAll(): Flow<List<SavedJob>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(job: SavedJob)
    @Query("DELETE FROM saved_jobs WHERE jobId = :jobId") suspend fun delete(jobId: Long)
}

@Database(entities = [SavedJob::class, CachedJob::class, DraftApplication::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() { abstract fun savedJobDao(): SavedJobDao }
