package meli.com.meliproducts.data.model

/**
 * Data transfer object to hold the result of a search
 */
data class ProductListDTO(
    val query: String,
    val results: List<ProductDTO>
)