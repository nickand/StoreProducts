package meli.com.meliproducts.data.network

import meli.com.meliproducts.data.model.ProductDTO
import meli.com.meliproducts.data.model.ProductListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Entry point for MercadoLibre's API
 */
interface MercadoLibreAPI {

    @GET("sites/MCO/search")
    suspend fun search(
        @Query("q") query: String
    ): ProductListDTO

    @GET("items/{id}")
    suspend fun getDetails(
        @Path("id") productId: String
    ): ProductDTO
}