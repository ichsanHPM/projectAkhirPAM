package com.example.projectakhirpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectakhirpam.PerpustakaanApplication
import com.example.projectakhirpam.ui.viewmodel.Penerbit.DetailPenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.Penerbit.HomePenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.Penerbit.InsertPenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.Penerbit.UpdatePenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.DetailKategoriViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.HomeKategoriViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.InsertKategoriViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.UpdateKategoriViewModel
import com.example.projectakhirpam.ui.viewmodel.penulis.DetailPenulisViewModel
import com.example.projectakhirpam.ui.viewmodel.penulis.HomePenulisViewModel
import com.example.projectakhirpam.ui.viewmodel.penulis.InsertPenulisViewModel
import com.example.projectakhirpam.ui.viewmodel.penulis.UpdatePenulisViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiPerpustakaan().container.bukuRepository) }
        initializer { InsertViewModel(aplikasiPerpustakaan().container.bukuRepository) }
        initializer { UpdateViewModel(aplikasiPerpustakaan().container.bukuRepository) }
        initializer { DetailViewModel(createSavedStateHandle(),
            aplikasiPerpustakaan().container.bukuRepository) }
        initializer { HomeKategoriViewModel(aplikasiPerpustakaan().container.kategoriRepository) }
        initializer { InsertKategoriViewModel(aplikasiPerpustakaan().container.kategoriRepository) }
        initializer { UpdateKategoriViewModel(aplikasiPerpustakaan().container.kategoriRepository) }
        initializer { DetailKategoriViewModel(createSavedStateHandle(),
            aplikasiPerpustakaan().container.kategoriRepository) }
        initializer { HomePenerbitViewModel(aplikasiPerpustakaan().container.penerbitRepository) }
        initializer { InsertPenerbitViewModel(aplikasiPerpustakaan().container.penerbitRepository) }
        initializer { UpdatePenerbitViewModel(aplikasiPerpustakaan().container.penerbitRepository) }
        initializer { DetailPenerbitViewModel(createSavedStateHandle(),
            aplikasiPerpustakaan().container.penerbitRepository) }
        initializer { HomePenulisViewModel(aplikasiPerpustakaan().container.penulisRepository) }
        initializer { InsertPenulisViewModel(aplikasiPerpustakaan().container.penulisRepository) }
        initializer { UpdatePenulisViewModel(aplikasiPerpustakaan().container.penulisRepository) }
        initializer { DetailPenulisViewModel(createSavedStateHandle(),
            aplikasiPerpustakaan().container.penulisRepository) }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): PerpustakaanApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApplication)