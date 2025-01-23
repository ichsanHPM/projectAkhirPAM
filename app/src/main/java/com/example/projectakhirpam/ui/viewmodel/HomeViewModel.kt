package com.example.projectakhirpam.ui.viewmodel

import com.example.projectakhirpam.model.Buku

sealed class HomeUiState{
    data class Success(val buku: List<Buku>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()
}

