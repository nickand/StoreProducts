package meli.com.meliproducts

import meli.com.meliproducts.data.database.ProductCache
import meli.com.meliproducts.data.database.ProductCacheMapper
import meli.com.meliproducts.data.model.Product
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.text.SimpleDateFormat
import java.util.Date

@RunWith(RobolectricTestRunner::class)
class ProductCacheMapperTest {

    private lateinit var productCacheMapper: ProductCacheMapper
    private lateinit var productCache: ProductCache
    private lateinit var product: Product

    @Before
    fun setUp() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        productCacheMapper = ProductCacheMapper()
        productCache = ProductCache(
            id = "id",
            title = "title",
            price = 10.0,
            currency = "COP",
            thumbnail = "thumbnail",
            pictures = "image1,image2",
            lastUpdated = dateFormat.format(currentDate),
            attributes = "[]",
            permalink = "permalink"
        )
        product = Product(
            id = productCache.id,
            title = productCache.title,
            price = productCache.price,
            currency = productCache.currency,
            thumbnail = productCache.thumbnail,
            pictures = productCache.pictures.split(","),
            lastUpdated = dateFormat.parse(productCache.lastUpdated),
            attributes = listOf(),
            permalink = productCache.permalink
        )
    }

    @Test
    fun product_cache_to_product_domain() {
        val result = productCacheMapper.mapToModel(productCache)
        assertEquals(product, result)
    }

    @Test
    fun product_domain_to_product_cache() {
        val result = productCacheMapper.mapFromModel(product)
        assertEquals(productCache, result)
    }
}