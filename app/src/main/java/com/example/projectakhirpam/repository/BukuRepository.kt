package com.example.projectakhirpam.repository

import com.example.projectakhirpam.model.Buku
import com.example.projectakhirpam.service.BukuService
import java.io.IOException

interface BukuRepository {
    suspend fun getBuku(): List<Buku>
    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(id_buku: String, buku: Buku)
    suspend fun deleteBuku(id_buku: String)
    suspend fun getBukuById(id_buku: String): Buku
}

class NetworkbukuRepository (private val bukuApiService: BukuService) : BukuRepository {
    override suspend fun insertBuku(buku: Buku) {
        bukuApiService.insertBuku(buku)
    }

    override suspend fun updateBuku(id_buku: String, buku: Buku) {
        bukuApiService.updateBuku(id_buku, buku)
    }

    override suspend fun deleteBuku(id_buku: String) {
        try {
            val response = bukuApiService.deleteBuku(id_buku)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete buku. HTTP Status code: ${response.code()}")
            } else{
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getBuku(): List<Buku> = bukuApiService.getBuku()
    override suspend fun getBukuById(id_buku: String): Buku {
        return bukuApiService.getBukuById(id_buku)
    }
}