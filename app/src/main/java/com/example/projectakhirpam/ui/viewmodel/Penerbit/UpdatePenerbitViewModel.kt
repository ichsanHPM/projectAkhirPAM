package com.example.projectakhirpam.ui.viewmodel.Penerbit


import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.repository.PenerbitRepository
import kotlinx.coroutines.launch


class UpdatePenerbitViewModel(
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdatePenerbitUiState())
        private set

    fun updateUpdatePenerbitState(updateUiEvent: UpdatePenerbitUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getPenerbitById(id_penerbit: String) {
        viewModelScope.launch {
            try {
                val penerbit = penerbitRepository.getPenerbitById(id_penerbit)
                uiState = UpdatePenerbitUiState(updateUiEvent = penerbit.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdatePenerbitUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updatePenerbit() {
        viewModelScope.launch {
            try {
                val penerbit = uiState.updateUiEvent.toPenerbit()
                penerbitRepository.updatePenerbit(penerbit.id_penerbit, penerbit)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}

data class UpdatePenerbitUiState(
    val updateUiEvent: UpdatePenerbitUiEvent = UpdatePenerbitUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdatePenerbitUiEvent(
    val id_penerbit: String = "",
    val nama_penerbit: String = "",
    val alamat_penerbit: String = "",
    val telepon_penerbit: String = ""
)

fun UpdatePenerbitUiEvent.toPenerbit(): Penerbit = Penerbit(
    id_penerbit = id_penerbit,
    nama_penerbit = nama_penerbit,
    alamat_penerbit = alamat_penerbit,
    telepon_penerbit = telepon_penerbit
)

fun Penerbit.toUpdateUiEvent(): UpdatePenerbitUiEvent = UpdatePenerbitUiEvent(
    id_penerbit = id_penerbit,
    nama_penerbit = nama_penerbit,
    alamat_penerbit = alamat_penerbit,
    telepon_penerbit = telepon_penerbit
)