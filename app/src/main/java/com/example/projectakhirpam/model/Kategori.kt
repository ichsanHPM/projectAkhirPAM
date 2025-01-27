package com.example.projectakhirpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kategori(
    @SerialName("id_kategori")
    val id_kategori: String,
    @SerialName("nama_kategori")
    val nama_kategori: String,
    @SerialName("deskripsi_kategori")
    val deskripsi_kategori: String
)
