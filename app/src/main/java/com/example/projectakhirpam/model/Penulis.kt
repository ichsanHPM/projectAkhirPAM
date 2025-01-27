package com.example.projectakhirpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Penulis(
    @SerialName("id_penulis")
    val id_penulis: String,
    @SerialName("nama_penulis")
    val nama_penulis: String,
    @SerialName("biografi")
    val biografi: String,
    @SerialName("kontak")
    val kontak: String
)
