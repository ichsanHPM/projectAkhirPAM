package com.example.projectakhirpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Penerbit(

    @SerialName("id_penerbit")
    val id_penerbit: String,
    @SerialName("nama_penerbit")
    val nama_penerbit: String,
    @SerialName("alamat_penerbit")
    val alamat_penerbit: String,
    @SerialName("telepon_penerbit")
    val telepon_penerbit: String
)
