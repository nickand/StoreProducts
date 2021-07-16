package meli.com.meliproducts

import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.ProductDTO
import meli.com.meliproducts.data.model.ProductDTOMapper
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Before
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProductDTOMapperTest {

    private lateinit var productDTOMapper: ProductDTOMapper
    private lateinit var productDTO: ProductDTO
    private lateinit var product: Product

    @Before
    fun setUp() {
        productDTOMapper = ProductDTOMapper()
        productDTO = ProductDTO(
            id = "id",
            title = "title",
            price = 10.0,
            currency = "COP",
            thumbnail = "thumbnail",
            pictures = listOf(),
            lastUpdated = Date(),
            attributes = listOf(),
            permalink = "permalink"
        )
        product = Product(
            id = productDTO.id,
            title = productDTO.title,
            price = productDTO.price,
            currency = productDTO.currency,
            thumbnail = productDTO.thumbnail,
            pictures = listOf(),
            lastUpdated = productDTO.lastUpdated,
            attributes = listOf(),
            permalink = productDTO.permalink
        )
    }

    @Test
    fun product_dto_to_product_domain() {
        val result = productDTOMapper.mapToModel(productDTO)
        assertEquals(product, result)
    }

    @Test
    fun product_domain_to_product_dto() {
        val result = productDTOMapper.mapFromModel(product)
        assertEquals(productDTO, result)
    }
}