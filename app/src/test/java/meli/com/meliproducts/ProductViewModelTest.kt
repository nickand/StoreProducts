package meli.com.meliproducts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import meli.com.meliproducts.data.model.Attribute
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.Resource
import meli.com.meliproducts.data.repository.ProductsRepository
import meli.com.meliproducts.ui.main.ProductViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks
import java.util.Date

class ProductViewModelTest {

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var productsRepository: ProductsRepository
    private lateinit var viewModel: ProductViewModel
    private lateinit var product: Product

    private val searchQuery = "product"

    @Before
    fun setup() {
        openMocks(this)
        viewModel = ProductViewModel(productsRepository)
        product = Product(
            id = "id",
            title = "title",
            price = 10.0,
            currency = "COP",
            thumbnail = "thumbnail",
            pictures = listOf("img1", "img2"),
            lastUpdated = Date(),
            attributes = listOf(Attribute("name", "value")),
            permalink = "permalink"
        )

    }

    @Test
    fun testGetProductList() = runBlockingTest {
        `when`(productsRepository.search(searchQuery)).thenReturn(
            Resource.Success(listOf(product))
        )
        viewModel.getSearchResults(searchQuery)
        verify(productsRepository).search(searchQuery)
        assertEquals(product, viewModel.productList.value?.data?.get(0))
    }

    @Test
    fun testGetProductListFailed() = runBlockingTest {
        `when`(productsRepository.search(searchQuery)).thenReturn(
            Resource.Error("")
        )
        viewModel.getSearchResults(searchQuery)
        verify(productsRepository).search(searchQuery)
        assertThat(
            viewModel.productList.value,
            IsInstanceOf.instanceOf(Resource.Error::class.java)
        )
    }

    @Test
    fun testGetProductDetails() = runBlockingTest {
        `when`(productsRepository.get(searchQuery)).thenReturn(
            Resource.Success(product)
        )
        viewModel.getDetails(searchQuery)
        verify(productsRepository).get(searchQuery)
        assertEquals(product, viewModel.product.value?.data)
    }

    @Test
    fun testGetProductDetailsFailed() = runBlockingTest {
        `when`(productsRepository.get(searchQuery)).thenReturn(
            Resource.Error("")
        )
        viewModel.getDetails(searchQuery)
        verify(productsRepository).get(searchQuery)
        Assert.assertThat(
            viewModel.product.value,
            IsInstanceOf.instanceOf(Resource.Error::class.java)
        )
    }
}