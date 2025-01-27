package com.example.projectakhirpam.ui.viewmodel.Penerbit


import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.repository.PenerbitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class HomePenerbitUiState {
    data class Success(val penerbit: List<Penerbit>) : HomePenerbitUiState()
    object Error : HomePenerbitUiState()
    object Loading : HomePenerbitUiState()
}

class HomePenerbitViewModel(private val penerbitRepository: PenerbitRepository) : ViewModel() {
    var penerbitUiState : HomePenerbitUiState by mutableStateOf(HomePenerbitUiState.Loading)
        private set

    init {
        getPenerbit()
    }

    fun getPenerbit() {
        viewModelScope.launch {
            penerbitUiState = HomePenerbitUiState.Loading
            penerbitUiState = try {
                HomePenerbitUiState.Success(penerbitRepository.getPenerbit())
            } catch (e: IOException) {
                HomePenerbitUiState.Error
            } catch (e: HttpException) {
                HomePenerbitUiState.Error
            }
        }
    }

    fun deletePenerbit(id_penerbit: String) {
        viewModelScope.launch {
            try {
                penerbitRepository.deletePenerbit(id_penerbit)
                getPenerbit()
            } catch (e: IOException) {
                penerbitUiState = HomePenerbitUiState.Error
            } catch (e: HttpException) {
                penerbitUiState = HomePenerbitUiState.Error
            }
        }
    }
}