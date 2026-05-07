package com.ElOuedUniv.maktaba.data.model
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class Book(
    val id: Int? = null, // تلقائي من قاعدة البيانات
    val title: String,
    val author: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("category_id") val categoryId: Int
)
