package com.example.projectakhirpam.service

import com.example.projectakhirpam.model.Penulis
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PenulisService {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("bacapenulis.php")
    suspend fun getPenulis(): List<Penulis>

    @GET("baca1penulis.php")
    suspend fun getPenulisById(@Query("id_penulis") id_buku: String): Penulis

    @POST("insertpenulis.php")
    suspend fun insertPenulis(@Body penulis: Penulis)

    @PUT("editpenulis.php")
    suspend fun updatePenulis(@Query("id_penulis")id_penulis: String, @Body penulis: Penulis)

    @DELETE("deletepenulis.php")
    suspend fun deletePenulis(@Query("id_penulis") id_penulis: String): retrofit2.Response<Void>

}