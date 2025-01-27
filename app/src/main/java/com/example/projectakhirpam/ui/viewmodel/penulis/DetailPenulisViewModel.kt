package com.example.projectakhirpam.ui.viewmodel.penulis

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Penulis
import com.example.projectakhirpam.repository.PenulisRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class DetailPenulisUiState {
    data class Success(val penulis: Penulis) : DetailPenulisUiState()
    data class Error(val message: String) : DetailPenulisUiState()
    object Loading : DetailPenulisUiState()
}

class DetailPenulisViewModel(
    savedStateHandle: SavedStateHandle,
    private val penulisRepository: PenulisRepository
) : ViewModel() {
    private val _id_penulis: String = checkNotNull(savedStateHandle["id_penulis"])

    private val _detailUiState = MutableStateFlow<DetailPenulisUiState>(DetailPenulisUiState.Loading)
    val detailPenulisUiState: StateFlow<DetailPenulisUiState> = _detailUiState.asStateFlow()

    init {
        getPenulisById(_id_penulis)
    }

    private fun getPenulisById(id_penulis: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailPenulisUiState.Loading
            _detailUiState.value = try {
                val penulis = penulisRepository.getPenulisById(id_penulis)
                DetailPenulisUiState.Success(penulis)
            } catch (e: IOException) {
                DetailPenulisUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailPenulisUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}
