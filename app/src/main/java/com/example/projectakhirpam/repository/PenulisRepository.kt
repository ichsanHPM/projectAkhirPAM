package com.example.projectakhirpam.repository

import com.example.projectakhirpam.model.Penulis
import com.example.projectakhirpam.service.PenulisService
import java.io.IOException


interface PenulisRepository {
    suspend fun getPenulis(): List<Penulis>
    suspend fun insertPenulis(penulis: Penulis)
    suspend fun updatePenulis(id_penulis: String, penulis: Penulis)
    suspend fun deletePenulis(id_penulis: String)
    suspend fun getPenulisById(id_penulis: String): Penulis
}

class NetworkPenulisRepository(private val penulisApiService: PenulisService) : PenulisRepository {
    override suspend fun insertPenulis(penulis: Penulis) {
        penulisApiService.insertPenulis(penulis)
    }

    override suspend fun updatePenulis(id_penulis: String, penulis: Penulis) {
        penulisApiService.updatePenulis(id_penulis, penulis)
    }

    override suspend fun deletePenulis(id_penulis: String) {
        try {
            val response = penulisApiService.deletePenulis(id_penulis)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete penulis. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPenulis(): List<Penulis> = penulisApiService.getPenulis()

    override suspend fun getPenulisById(id_penulis: String): Penulis {
        return penulisApiService.getPenulisById(id_penulis)
    }
}