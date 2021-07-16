package meli.com.meliproducts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import meli.com.meliproducts.data.model.Product
import meli.com.meliproducts.data.model.Resource
import meli.com.meliproducts.data.repository.ProductsRepository
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productsRepository: ProductsRepository) : ViewModel() {

    var query: MutableLiveData<String> = MutableLiveData("")

    private val _productList = MutableLiveData<Resource<List<Product?>>>()
    val productList: LiveData<Resource<List<Product?>>>
        get() = _productList

    private val _product = MutableLiveData<Resource<Product>>()
    val product: LiveData<Resource<Product>>
        get() = _product

    fun getSearchResults(searchTerms: String) {
        _productList.postValue(Resource.Loading())
        viewModelScope.launch {
            runCatching {
                _productList.postValue(productsRepository.search(searchTerms))
            }.onFailure { throwable ->
                _productList.postValue(Resource.Error(throwable.message ?: "Error fetching result list from service"))
            }
        }
    }

    fun getDetails(id: String) {
        viewModelScope.launch {
            runCatching {
                _product.postValue(Resource.Loading())
                _product.postValue(productsRepository.get(id))
            }.onFailure { throwable ->
                _productList.postValue(Resource.Error(throwable.message ?: "Error fetching result list from service"))
            }
        }
    }
}