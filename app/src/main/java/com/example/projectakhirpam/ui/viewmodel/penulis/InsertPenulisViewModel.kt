package com.example.projectakhirpam.ui.viewmodel.penulis

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Penulis
import com.example.projectakhirpam.repository.PenulisRepository
import kotlinx.coroutines.launch

class InsertPenulisViewModel(private val penulisRepository: PenulisRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertPenulisUiState())
        private set

    fun updateInsertPenulisState(insertPenulisUiEvent: InsertPenulisUiEvent) {
        uiState = InsertPenulisUiState(insertUiEvent = insertPenulisUiEvent)
    }

    suspend fun insertPenulis() {
        viewModelScope.launch {
            try {
                penulisRepository.insertPenulis(uiState.insertUiEvent.toPenulis())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPenulisUiState(
    val insertUiEvent: InsertPenulisUiEvent = InsertPenulisUiEvent()
)

data class InsertPenulisUiEvent(
    val id_penulis: String = "",
    val nama_penulis: String = "",
    val biografi: String = "",
    val kontak: String = ""
)

fun InsertPenulisUiEvent.toPenulis(): Penulis = Penulis(
    id_penulis = id_penulis,
    nama_penulis = nama_penulis,
    biografi = biografi,
    kontak = kontak
)

fun Penulis.toUiStatePenulis(): InsertPenulisUiState = InsertPenulisUiState(
    insertUiEvent = toInsertPenulisUiEvent()
)

fun Penulis.toInsertPenulisUiEvent(): InsertPenulisUiEvent = InsertPenulisUiEvent(
    id_penulis = id_penulis,
    nama_penulis = nama_penulis,
    biografi = biografi,
    kontak = kontak
)