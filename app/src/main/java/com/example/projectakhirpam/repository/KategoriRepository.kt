package com.example.projectakhirpam.repository

import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.service.KategoriService
import java.io.IOException

interface KategoriRepository {
    suspend fun getKategori(): List<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(id_kategori: String, kategori: Kategori)
    suspend fun deleteKategori(id_kategori: String)
    suspend fun getKategoriById(id_kategori: String): Kategori
}

class NetworkKategoriRepository(private val kategoriApiService: KategoriService) : KategoriRepository {
    override suspend fun insertKategori(kategori: Kategori) {
        kategoriApiService.insertKategori(kategori)
    }

    override suspend fun updateKategori(id_kategori: String, kategori: Kategori) {
        kategoriApiService.updateKategori(id_kategori, kategori)
    }

    override suspend fun deleteKategori(id_kategori: String) {
        try {
            val response = kategoriApiService.deleteKategori(id_kategori)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kategori. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKategori(): List<Kategori> = kategoriApiService.getKategori()

    override suspend fun getKategoriById(id_kategori: String): Kategori {
        return kategoriApiService.getKategoriById(id_kategori)
    }
}