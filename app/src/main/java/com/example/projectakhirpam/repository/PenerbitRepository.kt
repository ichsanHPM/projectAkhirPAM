package com.example.projectakhirpam.repository

import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.service.PenerbitService
import java.io.IOException


interface PenerbitRepository {
    suspend fun getPenerbit(): List<Penerbit>
    suspend fun insertPenerbit(penerbit: Penerbit)
    suspend fun updatePenerbit(id_penerbit: String, penerbit: Penerbit)
    suspend fun deletePenerbit(id_penerbit: String)
    suspend fun getPenerbitById(id_penerbit: String): Penerbit
}

class NetworkPenerbitRepository(private val penerbitApiService: PenerbitService) : PenerbitRepository {
    override suspend fun getPenerbit(): List<Penerbit> = penerbitApiService.getPenerbit()

    override suspend fun insertPenerbit(penerbit: Penerbit) {
        penerbitApiService.insertPenerbit(penerbit)
    }

    override suspend fun updatePenerbit(id_penerbit: String, penerbit: Penerbit) {
        penerbitApiService.updatePenerbit(id_penerbit, penerbit)
    }

    override suspend fun deletePenerbit(id_penerbit: String) {
        try {
            val response = penerbitApiService.deletePenerbit(id_penerbit)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete penerbit. HTTP Status code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPenerbitById(id_penerbit: String): Penerbit {
        return penerbitApiService.getPenerbitById(id_penerbit)
    }
}