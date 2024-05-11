package ardents.alexpolo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.alexpolo.Model.ProductModelItem
import ardents.alexpolo.Repository.ProductRepo
import ardents.alexpolo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel(){
    val productRepo=ProductRepo()
    val productData:LiveData<NetworkResult<List<ProductModelItem>>>
        get() = productRepo.productData

    fun getProduct(id:String){
        viewModelScope.launch(Dispatchers.IO) {
           productRepo.getProduct(id)
        }
    }
}