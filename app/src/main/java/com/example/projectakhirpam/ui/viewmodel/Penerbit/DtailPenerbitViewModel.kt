package com.example.projectakhirpam.ui.viewmodel.Penerbit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.repository.PenerbitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class DetailPenerbitUiState {
    data class Success(val penerbit: Penerbit) : DetailPenerbitUiState()
    data class Error(val message: String) : DetailPenerbitUiState()
    object Loading : DetailPenerbitUiState()
}

class DetailPenerbitViewModel(
    savedStateHandle: SavedStateHandle,
    private val penerbitRepository: PenerbitRepository
) : ViewModel() {

    private val _id_penerbit: String = checkNotNull(savedStateHandle["id_penerbit"])

    private val _detailUiState = MutableStateFlow<DetailPenerbitUiState>(DetailPenerbitUiState.Loading)
    val detailUiState: StateFlow<DetailPenerbitUiState> = _detailUiState.asStateFlow()

    init {
        getPenerbitById(_id_penerbit)
    }

    private fun getPenerbitById(id_penerbit: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailPenerbitUiState.Loading
            _detailUiState.value = try {
                val penerbit = penerbitRepository.getPenerbitById(id_penerbit)
                DetailPenerbitUiState.Success(penerbit)
            } catch (e: IOException) {
                DetailPenerbitUiState.Error("Terjadi kesalahan jaringan")
            } catch (e: HttpException) {
                DetailPenerbitUiState.Error("Terjadi kesalahan server")
            }
        }
    }
}