package com.example.projectakhirpam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Buku
import com.example.projectakhirpam.repository.BukuRepository
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val bk: BukuRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun updateUpdateBkState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getBukuById(id_buku: String) {
        viewModelScope.launch {
            try {
                val buku = bk.getBukuById(id_buku)
                uiState = UpdateUiState(updateUiEvent = buku.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateBk() {
        viewModelScope.launch {
            try {
                val buku = uiState.updateUiEvent.toBk()
                bk.updateBuku(buku.id_buku, buku)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}

data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdateUiEvent(
    val id_buku: String = "",
    val id_kategori: String = "",
    val id_penerbit: String = "",
    val id_penulis: String = "",
    val nama_buku: String = "",
    val status_buku: String = "",
    val tanggal_terbit: String = "",
    val deskripsi_buku: String = "",
)

fun UpdateUiEvent.toBk(): Buku = Buku(
    id_buku = id_buku,
    id_kategori = id_kategori,
    id_penerbit = id_penerbit,
    id_penulis = id_penulis,
    nama_buku = nama_buku,
    status_buku = status_buku,
    tanggal_terbit = tanggal_terbit,
    deskripsi_buku = deskripsi_buku,
)

fun Buku.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    id_buku = id_buku,
    id_kategori = id_kategori,
    id_penerbit = id_penerbit,
    id_penulis = id_penulis,
    nama_buku = nama_buku,
    status_buku = status_buku,
    tanggal_terbit = tanggal_terbit,
    deskripsi_buku = deskripsi_buku,
)