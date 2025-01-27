package com.example.projectakhirpam.service

import com.example.projectakhirpam.model.Kategori
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface KategoriService {
    //Memastikan komunikasi klien dan server dilakukan dengan format JSON
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("bacakategori.php")
    suspend fun getKategori(): List<Kategori>

    @GET("baca1kategori.php")
    suspend fun getKategoriById(@Query("id_kategori") id_kategori: String): Kategori

    @POST("insertkategori.php")
    suspend fun insertKategori(@Body kategori: Kategori)

    @PUT("editkategori.php")
    suspend fun updateKategori(@Query("id_kategori")id_kategori: String, @Body kategori: Kategori)

    @DELETE("deletekategori.php")
    suspend fun deleteKategori(@Query("id_kategori") id_kategori: String): retrofit2.Response<Void>

    //GET digunakan untuk mengambil data
    //POST digunakan untuk mengirim data
    //PUT digunakan untuk mengubah data
    //DELETE digunakan untuk menghapus data
}