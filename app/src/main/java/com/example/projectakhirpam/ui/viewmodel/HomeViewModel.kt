package com.example.projectakhirpam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.projectakhirpam.model.Buku
import com.example.projectakhirpam.repository.BukuRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val buku: List<Buku>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()
}

class HomeViewModel (private val bk: BukuRepository): ViewModel(){
    var bukuUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getBk()
    }

    fun getBk(){
        viewModelScope.launch{
            bukuUiState = HomeUiState.Loading
            bukuUiState = try {
                HomeUiState.Success(bk.getBuku())
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }

    fun deleteBk(id_buku: String){
        viewModelScope.launch{
            try {
                bk.deleteBuku(id_buku)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}