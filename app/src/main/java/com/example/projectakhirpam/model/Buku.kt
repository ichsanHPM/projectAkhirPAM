package com.example.projectakhirpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Buku(
    @SerialName("id_buku")
    val id_buku: String,
    @SerialName("nama_buku")
    val nama_buku: String,
    @SerialName("deskripsi_buku")
    val deskripsi_buku: String,
    @SerialName("tanggal_terbit")
    val tanggal_terbit: String,
    @SerialName("status_buku")
    val status_buku: String,
    @SerialName("id_kategori")
    val id_kategori: String,
    @SerialName("id_penerbit")
    val id_penerbit: String,
    @SerialName("id_penulis")
    val id_penulis: String,

)