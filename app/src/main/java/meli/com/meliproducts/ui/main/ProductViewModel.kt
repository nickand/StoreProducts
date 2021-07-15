package meli.com.meliproducts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.Resource
import meli.com.meliproducts.data.repository.ProductsRepository
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    var query: MutableLiveData<String> = MutableLiveData("")

    private val _productList =  MutableLiveData<Resource<List<Product?>>>()
    val productList: LiveData<Resource<List<Product?>>>
        get() = _productList

    private val _product =  MutableLiveData<Resource<Product>>()
    val product: LiveData<Resource<Product>>
        get() = _product

    fun getSearchResults(searchTerms: String) {
        CoroutineScope(coroutineDispatcher).launch {
            _productList.postValue(Resource.Loading())
            _productList.postValue(productsRepository.search(searchTerms))
        }
    }

    fun getDetails(id: String) {
        CoroutineScope(coroutineDispatcher).launch {
            _product.postValue(Resource.Loading())
            _product.postValue(productsRepository.get(id))
        }

    }
}