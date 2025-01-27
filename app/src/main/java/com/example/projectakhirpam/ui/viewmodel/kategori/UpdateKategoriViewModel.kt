package com.example.projectakhirpam.ui.viewmodel.kategori

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.repository.KategoriRepository
import kotlinx.coroutines.launch


class UpdateKategoriViewModel(
    private val kategoriRepository: KategoriRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateKategoriUiState())
        private set

    fun updateUpdateKategoriState(updateUiEvent: UpdateKategoriUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getKategoriById(id_kategori: String) {
        viewModelScope.launch {
            try {
                val kategori = kategoriRepository.getKategoriById(id_kategori)
                uiState = UpdateKategoriUiState(updateUiEvent = kategori.toUpdateKategoriUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateKategoriUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateKategori() {
        viewModelScope.launch {
            try {
                val kategori = uiState.updateUiEvent.toKategori()
                kategoriRepository.updateKategori(kategori.id_kategori, kategori)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}

data class UpdateKategoriUiState(
    val updateUiEvent: UpdateKategoriUiEvent = UpdateKategoriUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdateKategoriUiEvent(
    val id_kategori: String = "",
    val nama_kategori: String = "",
    val deskripsi_kategori: String = ""
)

fun UpdateKategoriUiEvent.toKategori(): Kategori = Kategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)

fun Kategori.toUpdateKategoriUiEvent(): UpdateKategoriUiEvent = UpdateKategoriUiEvent(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)