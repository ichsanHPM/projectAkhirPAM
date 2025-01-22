package com.example.projectakhirpam.repository

import com.example.projectakhirpam.model.Buku

interface bukuRepository {
    suspend fun getBuku(): List<Buku>
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(id_buku: String, buku: Buku)
    suspend fun deleteBuku(id_buku: String)
    suspend fun getBukuById(id_buku: String): Buku
}

class networkbukuRepository {
}