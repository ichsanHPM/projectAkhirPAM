package com.example.projectakhirpam.ui.viewmodel.penulis

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Penulis
import com.example.projectakhirpam.repository.PenulisRepository
import kotlinx.coroutines.launch


class UpdatePenulisViewModel(
    private val penulisRepository: PenulisRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdatePenulisUiState())
        private set

    fun updateUpdatePenulisState(updateUiEvent: UpdatePenulisUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getPenulisById(id_penulis: String) {
        viewModelScope.launch {
            try {
                val penulis = penulisRepository.getPenulisById(id_penulis)
                uiState = UpdatePenulisUiState(updateUiEvent = penulis.toUpdatePenulisUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdatePenulisUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updatePenulis() {
        viewModelScope.launch {
            try {
                val penulis = uiState.updateUiEvent.toPenulis()
                penulisRepository.updatePenulis(penulis.id_penulis, penulis)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}

data class UpdatePenulisUiState(
    val updateUiEvent: UpdatePenulisUiEvent = UpdatePenulisUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdatePenulisUiEvent(
    val id_penulis: String = "",
    val nama_penulis: String = "",
    val biografi: String = "",
    val kontak: String = ""
)

fun UpdatePenulisUiEvent.toPenulis(): Penulis = Penulis(
    id_penulis = id_penulis,
    nama_penulis = nama_penulis,
    biografi = biografi,
    kontak = kontak
)

fun Penulis.toUpdatePenulisUiEvent(): UpdatePenulisUiEvent = UpdatePenulisUiEvent(
    id_penulis = id_penulis,
    nama_penulis = nama_penulis,
    biografi = biografi,
    kontak = kontak
)