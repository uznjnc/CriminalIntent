package com.example.LintentCriminal

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.LintentCriminal.database.CrimeDatabase
import java.util.UUID




private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context,
                                          private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database: CrimeDatabase.CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase.CrimeDatabase::class.java,
           DATABASE_NAME
        )
        .addMigrations(migration_1_2, migration_2_3)
        .build()

   fun getCrimes(): Flow<List<Crimen>> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crimen = database.crimeDao().getCrime(id)

    fun updateCrime(crime: Crimen) {
         coroutineScope.launch {
             database.crimeDao().updateCrime(crime)
         }

        suspend fun addCrime(crime: Crimen){
            database.crimeDao().addCrime(crime)

        }
    }


    companion object {
        private var INSTANCE: CrimeRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }
        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}