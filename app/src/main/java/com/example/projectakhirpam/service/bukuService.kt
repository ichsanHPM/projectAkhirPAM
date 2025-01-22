package com.example.projectakhirpam.service

import com.example.projectakhirpam.model.Buku
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface bukuService{
    //Memastikan komunikasi klien dan server dilakukan dengan format JSON
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("bacabuku.php")
    suspend fun getBuku(): List<Buku>

    @GET("baca1buku.php")
    suspend fun getBukuById(@Query("id_buku") id_buku: String): Buku

    @POST("insertbuku.php")
    suspend fun insertBuku(@Body buku: Buku)

    @PUT("editbuku.php")
    suspend fun editBuku(@Query("id_buku")id_buku: String, @Body buku: Buku)

    @DELETE("deletebuku.php")
    suspend fun deleteBuku(@Query("id_buku") id_buku: String): retrofit2.Response<Void>

    //GET digunakan untuk mengambil data
    //POST digunakan untuk mengirim data
    //PUT digunakan untuk mengubah data
    //DELETE digunakan untuk menghapus data
}