package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.AddToCartModel
import ardents.alexpolo.Model.ProductDetailsModel
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Repository.ProductRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel(){
    val productRepo=ProductRepo()
    val productData:LiveData<NetworkResult<List<ProductModelItem>>>
        get() = productRepo.productData

    val productDetails:LiveData<NetworkResult<ProductDetailsModel>>
        get() = productRepo.productDetails

    val addToCart:LiveData<NetworkResult<AddToCartModel>>
        get() = productRepo.addToCart

    fun getProduct(id:String){
        viewModelScope.launch {
           productRepo.getProduct(id)
        }
    }

    fun getProductDetail(slugid:String){
        viewModelScope.launch{
            productRepo.getProductDetails(slugid)
        }
    }

    fun addToCart(token:String,productId:String,quantity:String){
        viewModelScope.launch{
            productRepo.addToCart(token,productId, quantity)
        }
    }
}