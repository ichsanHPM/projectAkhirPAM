package com.example.projectakhirpam.repository

import com.example.projectakhirpam.service.BukuService
import com.example.projectakhirpam.service.KategoriService
import com.example.projectakhirpam.service.PenerbitService
import com.example.projectakhirpam.service.PenulisService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bukuRepository: BukuRepository
    val penerbitRepository: PenerbitRepository
    val penulisRepository: PenulisRepository
    val kategoriRepository: KategoriRepository
}

class PerpustakaanContainer: AppContainer{
    private val baseUrl = "http://10.0.2.2/perpustakaan/"
    private val json = Json {ignoreUnknownKeys = true}
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val bukuService: BukuService by lazy {retrofit.create(BukuService::class.java)}
    private val penerbitService : PenerbitService by lazy {retrofit.create(PenerbitService::class.java)}
    private val penulisService : PenulisService by lazy { retrofit.create(PenulisService::class.java) }
    private val kategoriService : KategoriService by lazy { retrofit.create(KategoriService::class.java) }
    override val bukuRepository: BukuRepository by lazy {NetworkbukuRepository(bukuService)}
    override val penerbitRepository: PenerbitRepository by lazy { NetworkPenerbitRepository(penerbitService) }
    override val penulisRepository: PenulisRepository by lazy { NetworkPenulisRepository(penulisService) }
    override val kategoriRepository: KategoriRepository by lazy { NetworkKategoriRepository(kategoriService) }
}