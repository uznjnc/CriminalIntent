package com.example.LintentCriminal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*


class CrimeListViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val _crimes: MutableStateFlow<List<Crimen>> = MutableStateFlow(emptyList())
    val crimes: StateFlow<List<Crimen>>
        get() = _crimes.asStateFlow()

    init {
        viewModelScope.launch {
            crimeRepository.getCrimes().collect {
                _crimes.value = it
            }
        }

        }
    suspend fun addCrime(crime: Crimen){
        crimeRepository.addCrime(crime)
    }

    }
