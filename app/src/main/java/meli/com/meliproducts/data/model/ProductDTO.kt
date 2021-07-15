package meli.com.meliproducts.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * Data transfer objects to hold the details of a product
 */
data class ProductDTO(
    val id: String,
    val title: String,
    val price: Double,
    @SerializedName("currency_id")
    val currency: String,
    val permalink: String,
    val thumbnail: String,
    val pictures: List<PictureDTO>?,
    @SerializedName("last_updated")
    val lastUpdated: Date?,
    val attributes: List<AttributeDTO>?
)

data class AttributeDTO(
    val name: String,
    @SerializedName("value_name")
    val value: String?
)

data class PictureDTO(
    val url: String
)