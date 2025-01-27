package com.example.projectakhirpam.ui.viewmodel.penulis

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Penulis
import com.example.projectakhirpam.repository.PenulisRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePenulisUiState {
    data class Success(val penulis: List<Penulis>) : HomePenulisUiState()
    object Error : HomePenulisUiState()
    object Loading : HomePenulisUiState()
}

class HomePenulisViewModel(private val penulisRepository: PenulisRepository) : ViewModel() {
    var penulisUiState: HomePenulisUiState by mutableStateOf(HomePenulisUiState.Loading)
        private set

    init {
        getPenulis()
    }

    fun getPenulis() {
        viewModelScope.launch {
            penulisUiState = HomePenulisUiState.Loading
            penulisUiState = try {
                HomePenulisUiState.Success(penulisRepository.getPenulis())
            } catch (e: IOException) {
                HomePenulisUiState.Error
            } catch (e: HttpException) {
                HomePenulisUiState.Error
            }
        }
    }

    fun deletePenulis(id_penulis: String) {
        viewModelScope.launch {
            try {
                penulisRepository.deletePenulis(id_penulis)
                getPenulis()
            } catch (e: IOException) {
                penulisUiState = HomePenulisUiState.Error
            } catch (e: HttpException) {
                penulisUiState = HomePenulisUiState.Error
            }
        }
    }
}