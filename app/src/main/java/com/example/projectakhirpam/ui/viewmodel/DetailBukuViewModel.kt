package com.example.projectakhirpam.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Buku
import com.example.projectakhirpam.repository.BukuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val buku: Buku) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val bk: BukuRepository
) : ViewModel() {
    private val _id_buku: String = checkNotNull(savedStateHandle["id_buku"])

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    init {
        getBukuById(_id_buku)
    }

    private fun getBukuById(id_buku : String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                val buku = bk.getBukuById(id_buku)
                DetailUiState.Success(buku)
            } catch (e: IOException) {
                DetailUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}
