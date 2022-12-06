package com.example.LintentCriminal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.LintentCriminal.Crimen
import java.util.UUID



@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crimen>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Crimen

    @Update
    suspend fun updateCrime(crime: Crimen)

    @Insert
    suspend fun addCrime(crime: Crimen)

}
