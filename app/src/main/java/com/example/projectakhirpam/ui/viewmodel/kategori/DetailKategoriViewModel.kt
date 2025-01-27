package com.example.projectakhirpam.ui.viewmodel.kategori

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.repository.KategoriRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class DetailKategoriUiState {
    data class Success(val kategori: Kategori) : DetailKategoriUiState()
    data class Error(val message: String) : DetailKategoriUiState()
    object Loading : DetailKategoriUiState()
}

class DetailKategoriViewModel(
    savedStateHandle: SavedStateHandle,
    private val kategoriRepository: KategoriRepository
) : ViewModel() {
    private val _id_kategori: String = checkNotNull(savedStateHandle["id_kategori"])

    private val _detailUiState = MutableStateFlow<DetailKategoriUiState>(DetailKategoriUiState.Loading)
    val detailUiState: StateFlow<DetailKategoriUiState> = _detailUiState

    init {
        getKategoriById(_id_kategori)
    }

    private fun getKategoriById(id_kategori: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailKategoriUiState.Loading
            _detailUiState.value = try {
                val kategori = kategoriRepository.getKategoriById(id_kategori)
                DetailKategoriUiState.Success(kategori)
            } catch (e: IOException) {
                DetailKategoriUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailKategoriUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}