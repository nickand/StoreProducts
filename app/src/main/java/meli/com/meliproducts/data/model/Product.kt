package meli.com.meliproducts.data.model

import java.util.Date

/**
 * Domain object representing a MercadoLibre product
 */
data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val currency: String,
    val permalink: String,
    val thumbnail: String,
    val pictures: List<String>,
    val lastUpdated: Date?,
    val attributes: List<Attribute>?
)

data class Attribute(
    val name: String,
    val value: String?
)