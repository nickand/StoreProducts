package meli.com.meliproducts.data.repository

import meli.com.meliproducts.data.database.ProductCacheMapper
import meli.com.meliproducts.data.database.ProductDAO
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.network.MercadoLibreAPI
import meli.com.meliproducts.data.model.ProductDTOMapper
import meli.com.meliproducts.data.model.Resource

/**
 * Implements @see MercadoLibreAPI
 */
class ProductsRepositoryImp(
    private val mercadoLibreAPI: MercadoLibreAPI,
    private val productDAO: ProductDAO,
    private val dtoMapper: ProductDTOMapper,
    private val cacheMapper: ProductCacheMapper
) : ProductsRepository {

    /**
     * Will fetch results according to @param query, clear the database and then store the results
     * as the new cache data.
     */
    override suspend fun search(query: String): Resource<List<Product?>> {
        return try {
            val dtoList = mercadoLibreAPI.search(query).results
            val domainList = dtoMapper.toDomainList(dtoList)
            val cacheList = cacheMapper.fromList(domainList)
            productDAO.deleteAll()
            productDAO.save(*cacheList.toTypedArray())
            Resource.Success(domainList)
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching result list from service")
        }
    }

    /**
     * Will get a Product details from the database cache, in case the data is not complete, the rest
     * of the information will be fetched from the service.
     */
    override suspend fun get(productId: String): Resource<Product> {
        return try {
            var productCache = productDAO.loadResult(productId)
            if (productCache.lastUpdated == null) {
                val productDTO = mercadoLibreAPI.getDetails(productId)
                val product = dtoMapper.mapToModel(productDTO)
                productCache = cacheMapper.mapFromModel(product)
                productDAO.update(productCache)
                Resource.Success(product)
            } else {
                Resource.Success(cacheMapper.mapToModel(productCache))
            }
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message ?: "Error fetching product from database")
        }
    }
}