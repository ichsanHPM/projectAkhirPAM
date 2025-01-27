package com.example.projectakhirpam.service

import com.example.projectakhirpam.model.Penerbit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PenerbitService {

    //Memastikan komunikasi klien dan server dilakukan dengan format JSON
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("bacapenerbit.php")
    suspend fun getPenerbit(): List<Penerbit>

    @GET("baca1penerbit.php")
    suspend fun getPenerbitById(@Query("id_penerbit") id_penerbit: String): Penerbit

    @POST("insertpenerbit.php")
    suspend fun insertPenerbit(@Body penerbit: Penerbit)

    @PUT("editpenerbit.php")
    suspend fun updatePenerbit(@Query("id_penerbit")id_penerbit: String, @Body penerbit: Penerbit)

    @DELETE("deletepenerbit.php")
    suspend fun deletePenerbit(@Query("id_penerbit") id_penerbit: String): retrofit2.Response<Void>

}