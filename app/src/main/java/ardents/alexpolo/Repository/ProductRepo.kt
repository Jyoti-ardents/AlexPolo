package ardents.alexpolo.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult

class ProductRepo {
    val _productData=MutableLiveData<NetworkResult<List<ProductModelItem>>>()
    val productData:LiveData<NetworkResult<List<ProductModelItem>>>
        get() = _productData

    suspend fun getProduct(id:String){
        val response=RetrofitClient.apiServices.products(id)
        if (response.isSuccessful && response.body()!=null){
            _productData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _productData.postValue(NetworkResult.Error(response.errorBody().toString()))
        }
    }
}