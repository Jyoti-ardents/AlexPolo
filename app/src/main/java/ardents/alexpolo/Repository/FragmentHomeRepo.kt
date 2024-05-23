package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.BestSellingModel
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.Network.RetrofitClient
import ardents.alexpolo.utils.NetworkResult


class FragmentHomeRepo {
    val _categoryData=MutableLiveData<NetworkResult<List<CategoryModelItem>>>()
    val categoryData:LiveData<NetworkResult<List<CategoryModelItem>>>
        get() = _categoryData

   // val _bannerData=MutableLiveData<List<BannerModelItem>>()
    val _bannerData=MutableLiveData<NetworkResult<List<BannerModelItem>>>()
   // val bannerData:LiveData<List<BannerModelItem>>
       val bannerData:LiveData<NetworkResult<List<BannerModelItem>>>
        get() = _bannerData

    val _latest_product=MutableLiveData<NetworkResult<BestSellingModel>>()
    val latest_product:LiveData<NetworkResult<BestSellingModel>>
        get()=_latest_product

    val _topRatedProducts=MutableLiveData<NetworkResult<BestSellingModel>>()
    val topRatedProducts:LiveData<NetworkResult<BestSellingModel>>
        get()=_topRatedProducts

    val _bestSellingProduct=MutableLiveData<NetworkResult<BestSellingModel>>()
    val bestSellingProduct:LiveData<NetworkResult<BestSellingModel>>
        get()=_bestSellingProduct

    val _discountProduct=MutableLiveData<NetworkResult<BestSellingModel>>()
    val discountProduct:LiveData<NetworkResult<BestSellingModel>>
        get()=_discountProduct

    val _searchProduct=MutableLiveData<NetworkResult<BestSellingModel>>()
    val searchProduct:LiveData<NetworkResult<BestSellingModel>>
        get() = _searchProduct

    suspend fun getCategory(){
        val response=RetrofitClient.apiServices.getCategory()
        if (response.isSuccessful && response.body()!=null){
            _categoryData.postValue(NetworkResult.Success(response.body()!!))
        }else if (response.errorBody()!=null){
            _categoryData.postValue(NetworkResult.Error(response.errorBody().toString()))
        }

    }

    suspend fun getBanner(){
        val response=RetrofitClient.apiServices.bannerData("all")
        if (response.isSuccessful && response.body()!=null){
            Log.d("bannerdata","==========${response.body()}")
            //_bannerData.postValue(response.body()!!)

            _bannerData.postValue(NetworkResult.Success(response.body()!!))
        }

    }


    suspend fun getLatestProduct(){
        try {
            val response=RetrofitClient.apiServices.latestProduct()
            if (response.isSuccessful && response.body()!=null){
                _latest_product.postValue(NetworkResult.Success((response.body()!!)))
            }else if (response.errorBody()!=null){
                _latest_product.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _latest_product.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun getTopRatedProducts(){
        try {
            val response=RetrofitClient.apiServices.topRatedProducts()
            if (response.isSuccessful && response.body()!=null){
                _topRatedProducts.postValue(NetworkResult.Success(response.body()!!))
            }
            else if (response.errorBody()!=null){
                _topRatedProducts.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _topRatedProducts.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun getBestSellingProduct(){
        try {
            val response=RetrofitClient.apiServices.bestSellingProduct()
            if (response.isSuccessful && response.body()!=null){
                _bestSellingProduct.postValue(NetworkResult.Success(response.body()!!))
            }else if (response.errorBody()!=null){
                _bestSellingProduct.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _bestSellingProduct.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun getDiscountProduct(){
        try {
            val response=RetrofitClient.apiServices.discountProduct()
            if (response.isSuccessful && response.body()!=null){
               _discountProduct.postValue(NetworkResult.Success(response.body()!!))
            }else if(response.errorBody()!=null) {
                _discountProduct.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _discountProduct.postValue(NetworkResult.Error(e.message))
        }

    }


    suspend fun getSearchProduct(name:String){
        try {
            val response=RetrofitClient.apiServices.searchProduct(name)
            if (response.isSuccessful && response.body()!=null){
               _searchProduct.postValue(NetworkResult.Success(response.body()!!))
            }else if (response.errorBody()!=null){
                _searchProduct.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _searchProduct.postValue(NetworkResult.Error(e.message))
        }
    }
}