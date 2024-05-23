package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.AddToCartModel
import ardents.alexpolo.Model.ProductDetailsModel
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult

class ProductRepo {
    val _productData=MutableLiveData<NetworkResult<List<ProductModelItem>>>()
    val productData:LiveData<NetworkResult<List<ProductModelItem>>>
        get() = _productData

    val _productDetails=MutableLiveData<NetworkResult<ProductDetailsModel>>()
    val productDetails:LiveData<NetworkResult<ProductDetailsModel>>
        get() = _productDetails

    val _addToCart=MutableLiveData<NetworkResult<AddToCartModel>>()
    val addToCart:LiveData<NetworkResult<AddToCartModel>>
        get() = _addToCart

    suspend fun getProduct(id:String){
        val response=RetrofitClient.apiServices.products(id)
        if (response.isSuccessful && response.body()!=null){
            _productData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _productData.postValue(NetworkResult.Error(response.errorBody().toString()))
        }
    }

    suspend fun getProductDetails(slugId:String){
        try {
            val response=RetrofitClient.apiServices.ProductDetails(slugId)
            if (response.isSuccessful && response.body()!=null){
                _productDetails.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _productDetails.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
           _productDetails.postValue(NetworkResult.Error(e.message))
        }

    }

    suspend fun addToCart(token:String,productId:String,quantity:String){
        try{
            val response=RetrofitClient.apiServices.addToCart(token, productId, quantity)
            if (response.isSuccessful && response.body()!=null){
                _addToCart.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _addToCart.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _addToCart.postValue(NetworkResult.Error(e.message))
        }
    }


}