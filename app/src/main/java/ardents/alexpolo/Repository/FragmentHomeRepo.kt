package ardents.alexpolo.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.CategoryModelItem
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
}