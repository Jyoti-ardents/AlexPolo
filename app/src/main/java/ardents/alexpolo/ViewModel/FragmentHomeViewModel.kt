package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.BannerModelItem
import ardents.alexpolo.Model.CategoryModelItem
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
}