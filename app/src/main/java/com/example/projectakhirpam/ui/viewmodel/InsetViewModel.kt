package com.example.projectakhirpam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectakhirpam.model.Buku
import com.example.projectakhirpam.repository.BukuRepository
import kotlinx.coroutines.launch

class InsertViewModel (private val bk: BukuRepository):ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertBkState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertBk(){
        viewModelScope.launch {
            try {
                bk.insertBuku(uiState.insertUiEvent.toBk())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent : InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val id_buku: String = "",
    val nama_buku: String = "",
    val deskripsi_buku: String = "",
    val status_buku: String = "",
    val tanggal_terbit: String = "",
    val id_kategori: String = "",
    val id_penerbit: String = "",
    val id_penulis: String = "",
)

fun InsertUiEvent.toBk(): Buku = Buku(
    id_buku = id_buku,
    id_kategori = id_kategori,
    id_penerbit = id_penerbit,
    id_penulis = id_penulis,
    nama_buku = nama_buku,
    status_buku = status_buku,
    tanggal_terbit = tanggal_terbit,
    deskripsi_buku = deskripsi_buku,
)

fun Buku.toUiStateBk():InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Buku.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    id_buku = id_buku,
    id_kategori = id_kategori,
    id_penerbit = id_penerbit,
    id_penulis = id_penulis,
    nama_buku = nama_buku,
    status_buku = status_buku,
    tanggal_terbit = tanggal_terbit,
    deskripsi_buku = deskripsi_buku,

)