package com.example.projectakhirpam.ui.viewmodel.Penerbit

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.repository.PenerbitRepository
import kotlinx.coroutines.launch


class InsertPenerbitViewModel(private val penerbitRepository: PenerbitRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertPenerbitUiState())
        private set

    fun updateInsertPenerbitState(insertUiEvent: InsertPenerbitUiEvent) {
        uiState = InsertPenerbitUiState(insertUiEvent = insertUiEvent)
    }

    fun insertPenerbit() {
        viewModelScope.launch {
            try {
                penerbitRepository.insertPenerbit(uiState.insertUiEvent.toPenerbit())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPenerbitUiState(
    val insertUiEvent: InsertPenerbitUiEvent = InsertPenerbitUiEvent()
)

data class InsertPenerbitUiEvent(
    val id_penerbit: String = "",
    val nama_penerbit: String = "",
    val alamat_penerbit: String = "",
    val telepon_penerbit: String = ""
)

fun InsertPenerbitUiEvent.toPenerbit(): Penerbit = Penerbit(
    id_penerbit = id_penerbit,
    nama_penerbit = nama_penerbit,
    alamat_penerbit = alamat_penerbit,
    telepon_penerbit = telepon_penerbit
)

fun Penerbit.toUiStatePenerbit(): InsertPenerbitUiState = InsertPenerbitUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Penerbit.toInsertUiEvent(): InsertPenerbitUiEvent = InsertPenerbitUiEvent(
    id_penerbit = id_penerbit,
    nama_penerbit = nama_penerbit,
    alamat_penerbit = alamat_penerbit,
    telepon_penerbit = telepon_penerbit
)