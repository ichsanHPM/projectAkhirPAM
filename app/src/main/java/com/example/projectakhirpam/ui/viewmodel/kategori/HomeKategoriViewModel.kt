package com.example.projectakhirpam.ui.viewmodel.kategori

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.repository.KategoriRepository
import kotlinx.coroutines.launch
import java.io.IOException


sealed class HomeKategoriUiState {
    data class Success(val kategori: List<Kategori>) : HomeKategoriUiState()
    object Error : HomeKategoriUiState()
    object Loading : HomeKategoriUiState()
}

class HomeKategoriViewModel(private val kategoriRepository: KategoriRepository) : ViewModel() {
    var kategoriUIState: HomeKategoriUiState by mutableStateOf(HomeKategoriUiState.Loading)
        private set

    init {
        getKategori()
    }

    fun getKategori() {
        viewModelScope.launch {
            kategoriUIState = HomeKategoriUiState.Loading
            kategoriUIState = try {
                HomeKategoriUiState.Success(kategoriRepository.getKategori())
            } catch (e: IOException) {
                HomeKategoriUiState.Error
            } catch (e: HttpException) {
                HomeKategoriUiState.Error
            }
        }
    }

    fun deleteKategori(id_kategori: String) {
        viewModelScope.launch {
            try {
                kategoriRepository.deleteKategori(id_kategori)
                getKategori()
            } catch (e: IOException) {
                kategoriUIState = HomeKategoriUiState.Error
            } catch (e: HttpException) {
                kategoriUIState = HomeKategoriUiState.Error
            }
        }
    }
}