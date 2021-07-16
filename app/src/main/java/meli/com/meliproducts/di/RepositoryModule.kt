package meli.com.meliproducts.di

import meli.com.meliproducts.data.repository.ProductsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import meli.com.meliproducts.data.database.ProductCacheMapper
import meli.com.meliproducts.data.database.ProductDAO
import meli.com.meliproducts.data.network.MercadoLibreAPI
import meli.com.meliproducts.data.model.ProductDTOMapper
import meli.com.meliproducts.data.repository.ProductsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        api: MercadoLibreAPI,
        dtoMapper: ProductDTOMapper,
        productDAO: ProductDAO,
        cacheMapper: ProductCacheMapper
    ): ProductsRepository {
        return ProductsRepositoryImp(
            mercadoLibreAPI = api,
            dtoMapper = dtoMapper,
            productDAO = productDAO,
            cacheMapper = cacheMapper
        )
    }
}

