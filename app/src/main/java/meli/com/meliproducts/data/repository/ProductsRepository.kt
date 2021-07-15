package meli.com.meliproducts.data.repository

import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.Resource

/**
 * Data access functions
 */
interface ProductsRepository {
    suspend fun search(query: String) : Resource<List<Product?>>
    suspend fun get(query: String) : Resource<Product>
}