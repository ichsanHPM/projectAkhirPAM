package com.example.projectakhirpam.ui.viewmodel.kategori

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.repository.KategoriRepository
import kotlinx.coroutines.launch


class InsertKategoriViewModel(private val kategoriRepository: KategoriRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertKategoriUiState())
        private set

    fun updateInsertKategoriState(insertUiEvent: InsertKategoriUiEvent) {
        uiState = InsertKategoriUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKategori() {
        viewModelScope.launch {
            try {
                kategoriRepository.insertKategori(uiState.insertUiEvent.toKategori())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertKategoriUiState(
    val insertUiEvent: InsertKategoriUiEvent = InsertKategoriUiEvent()
)

data class InsertKategoriUiEvent(
    val id_kategori: String = "",
    val nama_kategori: String = "",
    val deskripsi_kategori: String = ""
)

fun InsertKategoriUiEvent.toKategori(): Kategori = Kategori(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)

fun Kategori.toUiStateKategori(): InsertKategoriUiState = InsertKategoriUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Kategori.toInsertUiEvent(): InsertKategoriUiEvent = InsertKategoriUiEvent(
    id_kategori = id_kategori,
    nama_kategori = nama_kategori,
    deskripsi_kategori = deskripsi_kategori
)