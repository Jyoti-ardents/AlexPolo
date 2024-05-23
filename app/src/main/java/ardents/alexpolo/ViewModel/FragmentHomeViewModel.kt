package ardents.alexpolo.ViewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.BestSellingModel
import ardents.alexpolo.Model.CategoryModelItem
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Model.ProductsItem
import ardents.alexpolo.Repository.FragmentHomeRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentHomeViewModel: ViewModel() {
    val fragmentHomeRepo=FragmentHomeRepo()
    val categoryData:LiveData<NetworkResult<List<CategoryModelItem>>>
        get() = fragmentHomeRepo.categoryData
    val bannerData:LiveData<NetworkResult<List<BannerModelItem>>>
        get() = fragmentHomeRepo.bannerData

    val latestProduct:LiveData<NetworkResult<BestSellingModel>>
        get() = fragmentHomeRepo.latest_product

    val topRatedProducts:LiveData<NetworkResult<BestSellingModel>>
        get() = fragmentHomeRepo.topRatedProducts

    val bestSellingProduct:LiveData<NetworkResult<BestSellingModel>>
        get() = fragmentHomeRepo.bestSellingProduct

    val discountProduct:LiveData<NetworkResult<BestSellingModel>>
        get() = fragmentHomeRepo.discountProduct

    val searchProduct:LiveData<NetworkResult<BestSellingModel>>
        get() = fragmentHomeRepo.searchProduct


    fun getCategory(){
        viewModelScope.launch( Dispatchers.IO) {
            fragmentHomeRepo.getCategory()
        }
    }

    fun getBanner(){
        viewModelScope.launch(Dispatchers.IO){
            fragmentHomeRepo.getBanner()
        }
    }

    fun getLatestProduct(){
        viewModelScope.launch (Dispatchers.IO){
            fragmentHomeRepo.getLatestProduct()
        }
    }

    fun getTopRatedProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            fragmentHomeRepo.getTopRatedProducts()
        }
    }

    fun getBestSellingProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            fragmentHomeRepo.getBestSellingProduct()
        }
    }

    fun getDiscountProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            fragmentHomeRepo.getDiscountProduct()
        }

    }


    fun getSearchProduct(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            fragmentHomeRepo.getSearchProduct(name)
        }
    }
}